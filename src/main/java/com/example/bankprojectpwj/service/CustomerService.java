package com.example.bankprojectpwj.service;

import com.example.bankprojectpwj.exceptions.BankBranchNotFound;
import com.example.bankprojectpwj.exceptions.CustomerAlreadyExists;
import com.example.bankprojectpwj.exceptions.CustomerNotFoundException;
import com.example.bankprojectpwj.model.BankBranch;
import com.example.bankprojectpwj.model.Customer;
import com.example.bankprojectpwj.repository.BankBranchRepository;
import com.example.bankprojectpwj.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BankBranchRepository bankBranchRepository;

    public CustomerService(CustomerRepository customerRepository, BankBranchRepository bankBranchRepository) {
        this.customerRepository = customerRepository;
        this.bankBranchRepository = bankBranchRepository;
    }

    public Customer saveCustomer(Customer customer, int bankBranchId) {
        Optional<Customer> optionlCustomer = customerRepository.findCustomerByCnp(customer.getCnp());
        if(optionlCustomer.isPresent()){
            throw new CustomerAlreadyExists(customer.getCnp());
        }

        BankBranch bankBranch = bankBranchRepository.findById(bankBranchId)
                .orElseThrow(() -> new BankBranchNotFound(bankBranchId));

        customer.setBankBranch(bankBranch);
        customer.setStatus("ACTIVE");
        return customerRepository.save(customer);
    }

    public Customer retrieveCustomerById(int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isPresent()){
            return customerOptional.get();
        } else {
            throw new CustomerNotFoundException(id);
        }
    }

    public List<Customer> retrieveCustomersStartingWith(String prefix) {
        return customerRepository.findCustomerByLastNameStartingWith(prefix);
    }

    public List<Customer> retrieveCustomersByBankBranch(String bankBranchName) {
        BankBranch bankBranch = bankBranchRepository.findBankBranchByName(bankBranchName);
        return customerRepository.findCustomerByBankBranch(bankBranch);
    }
}
