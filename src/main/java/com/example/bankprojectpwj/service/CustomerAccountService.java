package com.example.bankprojectpwj.service;

import com.example.bankprojectpwj.exceptions.AccountNotFoundException;
import com.example.bankprojectpwj.exceptions.CustomerNotFoundException;
import com.example.bankprojectpwj.model.Account;
import com.example.bankprojectpwj.model.Customer;
import com.example.bankprojectpwj.model.CustomerAccount;
import com.example.bankprojectpwj.repository.AccountRepository;
import com.example.bankprojectpwj.repository.CustomerAccountRepository;
import com.example.bankprojectpwj.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerAccountService {
    Logger logger = LoggerFactory.getLogger(CustomerAccountService.class);

    private final CustomerAccountRepository customerAccountRepository;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public CustomerAccountService(CustomerAccountRepository customerAccountRepository, AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.customerAccountRepository = customerAccountRepository;
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public List<CustomerAccount> retrieveAllCustomerAccounts() {
        return customerAccountRepository.findAll();
    }

    public CustomerAccount addCustomerAccount(CustomerAccount customerAccount) {
//        logger.info(" customer account {}", customerAccount.getIsAccountHolder());
        Account account = accountRepository.findById(customerAccount.getCustomerAccountId().getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(customerAccount.getCustomerAccountId().getAccountId()));
        Customer customer = customerRepository.findById(customerAccount.getCustomerAccountId().getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(customerAccount.getCustomerAccountId().getCustomerId()));

        customerAccount.setAccount(account);
        customerAccount.setCustomer(customer);

        customerAccountRepository.save(customerAccount);
        return customerAccount;

    }
}
