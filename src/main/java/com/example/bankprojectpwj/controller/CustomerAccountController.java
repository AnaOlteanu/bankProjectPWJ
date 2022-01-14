package com.example.bankprojectpwj.controller;

import com.example.bankprojectpwj.dto.CustomerAccountRequest;
import com.example.bankprojectpwj.mapper.CustomerAccountMapper;
import com.example.bankprojectpwj.model.CustomerAccount;
import com.example.bankprojectpwj.service.CustomerAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customerAccounts")
@Validated
public class CustomerAccountController {

    private final CustomerAccountService customerAccountService;
    private final CustomerAccountMapper customerAccountMapper;
    Logger logger = LoggerFactory.getLogger(CustomerAccountService.class);


    public CustomerAccountController(CustomerAccountService customerAccountService, CustomerAccountMapper customerAccountMapper) {
        this.customerAccountService = customerAccountService;
        this.customerAccountMapper = customerAccountMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CustomerAccount>> retrieveAllCustomerAccounts (){
        return ResponseEntity.ok().body(customerAccountService.retrieveAllCustomerAccounts());
    }

    @PostMapping("/new")
    public ResponseEntity<CustomerAccount> addCustomerAccount(@Valid @RequestBody CustomerAccountRequest customerAccountRequest){

//        logger.info(" customer account holder {}", customerAccountRequest.getIsHolder());
//        logger.info(" customer account holder acc {}", customerAccountRequest.getAccountId());
//        logger.info(" customer account holder cust {}", customerAccountRequest.getCustomerId());

        CustomerAccount customerAccount = customerAccountMapper.customerAccountRequestToCustomerAccount(customerAccountRequest);
        return ResponseEntity.ok().body(customerAccountService.addCustomerAccount(customerAccount));
    }

}
