package com.example.bankprojectpwj.service;

import com.example.bankprojectpwj.dto.TransactionRequest;
import com.example.bankprojectpwj.exceptions.AccountNotFoundException;
import com.example.bankprojectpwj.exceptions.CustomerNotFoundException;
import com.example.bankprojectpwj.exceptions.NoAccountsFoundForTheCustomer;
import com.example.bankprojectpwj.exceptions.TransacationNotFoundException;
import com.example.bankprojectpwj.model.*;
import com.example.bankprojectpwj.repository.AccountRepository;
import com.example.bankprojectpwj.repository.CustomerRepository;
import com.example.bankprojectpwj.repository.TransactionDetailsRepository;
import com.example.bankprojectpwj.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailsRepository transactionDetailsRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public TransactionService(TransactionRepository transactionRepository, TransactionDetailsRepository transactionDetailsRepository, AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionDetailsRepository = transactionDetailsRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public List<Transaction> retrieveAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction saveTransaction(Transaction transaction, TransactionDetails transactionDetails, int accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        transaction.setAccount(account);
        transaction.setTransactionDetails(transactionDetails);
        transaction.setStatus("PENDING");

        transactionDetailsRepository.save(transactionDetails);
        return transactionRepository.save(transaction);


    }

    public Transaction getTransactionById(int id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isPresent()){
            return transaction.get();
        }
        throw new TransacationNotFoundException(id);
    }

    public List<Transaction> retrievePendingTransactions() {
        return transactionRepository.getTransactionByStatusEquals("PENDING");
    }

    @Transactional
    public String settleTransaction(int transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransacationNotFoundException(transactionId));
        Account account = transaction.getAccount();

        if(account.getStatus().equals("ACTIVE") && account.getBalance() > transaction.getAmount()) {
            accountRepository.updateBalance(-transaction.getAmount(), account.getAccountId());
            transactionRepository.updateStatus(transactionId, "SETTLED");
            return "SETTLED";
        } else {
            transactionRepository.updateStatus(transactionId, "REJECTED");
            return "REJECTED";
        }

    }

    public List<Transaction> retrieveTransactionsByCustomer(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        List<CustomerAccount> customerAccounts = customer.getCustomerAccounts();
        if(customerAccounts.size() == 0){
            throw new NoAccountsFoundForTheCustomer(customerId);
        }

        List<Account> accounts = new ArrayList<>();
        for(CustomerAccount customerAccount : customerAccounts){
            accounts.add(customerAccount.getAccount());
        }

        List<Transaction> transactions = new ArrayList<>();
        for(Account account: accounts){
            List<Transaction> transactionsForAccount = account.getTransactions();
            for(Transaction transaction : transactionsForAccount){
                transactions.add(transaction);
            }
        }

        return transactions;


    }
}
