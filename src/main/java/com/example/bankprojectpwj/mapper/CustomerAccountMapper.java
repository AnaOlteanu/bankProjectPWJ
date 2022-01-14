package com.example.bankprojectpwj.mapper;

import com.example.bankprojectpwj.dto.CustomerAccountRequest;

import com.example.bankprojectpwj.model.CustomerAccount;
import com.example.bankprojectpwj.model.CustomerAccountId;
import org.springframework.stereotype.Component;

@Component
public class CustomerAccountMapper {

    public CustomerAccount customerAccountRequestToCustomerAccount(CustomerAccountRequest customerAccountRequest){
        CustomerAccountId customerAccountId = new CustomerAccountId(customerAccountRequest.getCustomerId(),
                customerAccountRequest.getAccountId());
        return new CustomerAccount(customerAccountId, customerAccountRequest.getIsHolder());
    }
}
