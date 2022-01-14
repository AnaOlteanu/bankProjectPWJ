package com.example.bankprojectpwj.repository;

import com.example.bankprojectpwj.model.BankBranch;
import com.example.bankprojectpwj.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findCustomerByBankBranch(BankBranch bankBranch);
    List<Customer> findCustomerByLastNameStartingWith(String prefix);

    Optional<Customer> findCustomerByCnp(String cnp);
}
