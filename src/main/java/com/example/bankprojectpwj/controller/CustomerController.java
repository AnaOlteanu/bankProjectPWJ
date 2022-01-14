package com.example.bankprojectpwj.controller;

import com.example.bankprojectpwj.model.Customer;
import com.example.bankprojectpwj.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    public final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/new")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer, @RequestParam int bankBranchId){
        Customer newCustomer = customerService.saveCustomer(customer, bankBranchId);
        return ResponseEntity.created(URI.create("/customers/" + newCustomer.getCustomerId()))
                .body(newCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> retrieveCustomerById(@PathVariable int id){
        return ResponseEntity.ok().body(customerService.retrieveCustomerById(id));
    }

    @GetMapping("/nameStartingWith/{prefix}")
    public ResponseEntity<List<Customer>> retrieveCustomersStartingWith(@PathVariable String prefix){
        return ResponseEntity.ok().body(customerService.retrieveCustomersStartingWith(prefix));
    }

    @GetMapping("/listByBank/{bankBranchName}")
    public ResponseEntity<List<Customer>> retrieveCustomersByBankBranch(@PathVariable String bankBranchName){
        return ResponseEntity.ok()
                .body(customerService.retrieveCustomersByBankBranch(bankBranchName));
    }
}
