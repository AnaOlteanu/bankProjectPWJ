package com.example.bankprojectpwj.service;

import com.example.bankprojectpwj.exceptions.AccountNotFoundException;
import com.example.bankprojectpwj.exceptions.CustomerNotFoundException;
import com.example.bankprojectpwj.model.Account;
import com.example.bankprojectpwj.model.Customer;
import com.example.bankprojectpwj.model.CustomerAccount;
import com.example.bankprojectpwj.repository.AccountRepository;
import com.example.bankprojectpwj.repository.CustomerAccountRepository;
import com.example.bankprojectpwj.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CustomerAccountRepository customerAccountRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, CustomerAccountRepository customerAccountRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.customerAccountRepository = customerAccountRepository;
    }

    public Account saveAccount(Account account){
        account.setStatus("ACTIVE");
        Account accountSaved = accountRepository.save(account);
        return accountSaved;

    }

    public Account getAccountById(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if(accountOptional.isPresent()){
            return accountOptional.get();
        } else {
            throw new AccountNotFoundException(id);
        }
    }

    public List<Account> retrieveAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> getAccountByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        List<CustomerAccount> customerAccounts = customerAccountRepository.findByCustomer(customer);
        List<Account> accountList = new ArrayList<>();

        for(CustomerAccount customerAccount: customerAccounts){
            accountList.add(customerAccount.getAccount());
        }
        return accountList;
    }

    @Transactional
    public void deactivateAccount(int id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        accountRepository.changeAccountStatus(id, "DEACTIVATED");
    }

    public void deleteAccountById(int accountId) {
        Account account = accountRepository.findById(accountId)
                        .orElseThrow(() -> new AccountNotFoundException(accountId));
        accountRepository.delete(account);
    }

    @Transactional
    public void addAmount(int accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        accountRepository.updateBalance(amount, accountId);
    }
}
