package com.example.bankprojectpwj.mapper;

import com.example.bankprojectpwj.dto.TransactionRequest;
import com.example.bankprojectpwj.model.Transaction;
import com.example.bankprojectpwj.model.TransactionDetails;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction transactionRequestToTransaction(TransactionRequest transactionRequest){
        return new Transaction(transactionRequest.getDate(),
                transactionRequest.getAmount());
    }

    public TransactionDetails transactionRequestToTransactionDetails(TransactionRequest transactionRequest){
        return new TransactionDetails(transactionRequest.getProductName(), transactionRequest.getQuantity());
    }
}
