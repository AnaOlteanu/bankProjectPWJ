package com.example.bankprojectpwj.service;

import com.example.bankprojectpwj.exceptions.AccountNotFoundException;
import com.example.bankprojectpwj.exceptions.CustomerNotFoundException;
import com.example.bankprojectpwj.model.*;
import com.example.bankprojectpwj.repository.AccountRepository;
import com.example.bankprojectpwj.repository.CustomerAccountRepository;
import com.example.bankprojectpwj.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerAccountRepository customerAccountRepository;

    @Test
    @DisplayName("Save account happy flow")
    void testSaveAccountHappyFlow() {

        Account account = new Account(LocalDate.of(2019, 4, 10), 2000, AccountType.CREDIT);
        Account accountSaved = new Account(LocalDate.of(2019, 4, 10), 2000, AccountType.CREDIT);
        accountSaved.setAccountId(1);

        when(accountRepository.save(account)).thenReturn(accountSaved);

        Account result = accountService.saveAccount(account);

        assertNotNull(result);
        assertEquals(accountSaved.getAccountId(), result.getAccountId());
        assertEquals(account.getBalance(), result.getBalance());
        assertEquals(account.getCreationDate(), result.getCreationDate());
        assertEquals(account.getType(), result.getType());
    }


    @Test
    @DisplayName("Get account by id happy flow")
    void testGetAccountByIdHappyFlow(){
        int accountId = 1;
        Account account = new Account(LocalDate.of(2019, 4, 10), 2000, AccountType.CREDIT);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        Account result = accountService.getAccountById(accountId);

        assertNotNull(result);
        assertEquals(account.getAccountId(), result.getAccountId());
    }

    @Test
    @DisplayName("Get account by id negative flow")
    void testGetAccountByIdNegativeFlow(){
        int accountId = 1;
        Optional<Account> account = Optional.empty();
        String response = "Account with id 1 does not exist!";
        when(accountRepository.findById(accountId)).thenReturn(account);

        AccountNotFoundException result = assertThrows(
                AccountNotFoundException.class,
                () -> accountService.getAccountById(accountId));

        assertEquals(response, result.getMessage());
    }

    @Test
    @DisplayName("Get account by customer id happy flow")
    void testGetAccountByCustomerIdHappyFlow(){
        int customerId = 10;
        Customer customer = new Customer("Popescu", "Tudor", "0728765432",
                LocalDate.of(1960, 8, 16), "1600816670098");
        CustomerAccount customerAccount1 = new CustomerAccount(new CustomerAccountId(customerId, 1), "da", customer,
                new Account(LocalDate.of(2019, 4, 10), 2000, AccountType.CREDIT));
        List<CustomerAccount> customerAccounts = new ArrayList<>();
        customerAccounts.add(customerAccount1);

        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(LocalDate.of(2019, 4, 10), 2000, AccountType.CREDIT));

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerAccountRepository.findByCustomer(customer)).thenReturn(customerAccounts);

        List<Account> result = accountService.getAccountByCustomerId(customerId);

        assertEquals(accountList.get(0).getAccountId(), result.get(0).getAccountId());
    }

    @Test
    @DisplayName("Get account by customer id negative flow flow")
    void testGetAccountByCustomerIdNegativeFlow(){
        int customerId = 10;
        when(customerRepository.findById(customerId)).thenThrow(new CustomerNotFoundException(customerId));
        try{
            List<Account> result = accountService.getAccountByCustomerId(customerId);
        } catch (CustomerNotFoundException e){
            assertEquals("Customer with id 10 does not exist!", e.getMessage());
        }
    }

    @Test
    @DisplayName("Deactivate account happy flow")
    void testDeactivateAccountHappyFlow(){
        int accountId = 1;
        Account account =  new Account(LocalDate.of(2019, 4, 10), 2000, AccountType.CREDIT);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        doNothing().when(accountRepository).changeAccountStatus(accountId, "DEACTIVATED");

        accountService.deactivateAccount(accountId);

        verify(accountRepository, times(1)).changeAccountStatus(accountId, "DEACTIVATED");
    }

    @Test
    @DisplayName("Deactivate account negative flow")
    void testDeactivateAccountNegativeFlow(){
        int accountId = 1;

        when(accountRepository.findById(accountId)).thenThrow(new AccountNotFoundException(accountId));
        try{
            accountService.deactivateAccount(accountId);
        } catch (AccountNotFoundException e){
            assertEquals("Account with id 1 does not exist!", e.getMessage());
        }
    }

}
