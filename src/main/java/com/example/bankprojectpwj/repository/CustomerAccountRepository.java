package com.example.bankprojectpwj.repository;

import com.example.bankprojectpwj.model.Customer;
import com.example.bankprojectpwj.model.CustomerAccount;
import com.example.bankprojectpwj.model.CustomerAccountId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, CustomerAccountId> {

    List<CustomerAccount> findByCustomer(Customer customer);
}
