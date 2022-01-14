package com.example.bankprojectpwj.controller;


import com.example.bankprojectpwj.dto.TransactionRequest;
import com.example.bankprojectpwj.mapper.TransactionMapper;
import com.example.bankprojectpwj.model.Transaction;
import com.example.bankprojectpwj.model.TransactionDetails;
import com.example.bankprojectpwj.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Validated
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<Transaction> addTransaction( @Valid @RequestBody TransactionRequest transactionRequest){
        Transaction transaction = transactionMapper.transactionRequestToTransaction(transactionRequest);
        TransactionDetails transactionDetails = transactionMapper.transactionRequestToTransactionDetails(transactionRequest);
        Transaction newTransaction = transactionService.saveTransaction(transaction, transactionDetails, transactionRequest.getAccountId());

        return ResponseEntity.created(URI.create("/transactions/" + newTransaction.getTransactionId()))
                .body(newTransaction);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable @Valid int id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Transaction>> retrieveAllTransactions(){
        return ResponseEntity.ok().body(transactionService.retrieveAllTransactions());
    }

    @GetMapping("/listPendingTransactions")
    public ResponseEntity<List<Transaction>> retrievePendingTransactions(){
        return ResponseEntity.ok().body(transactionService.retrievePendingTransactions());
    }

    @GetMapping("/listTransactionsByCustomerId/{id}")
    public ResponseEntity<List<Transaction>> retrieveTransactionsByCustomers(@PathVariable(name = "id") int customerId){
        return ResponseEntity.ok().body(transactionService.retrieveTransactionsByCustomer(customerId));
    }

    @PatchMapping("/settleTransaction/{id}")
    public ResponseEntity<String> settleTransaction(@PathVariable("id") int transactionId){
        String response = transactionService.settleTransaction(transactionId);

        if(response.equals("SETTLED"))
            return ResponseEntity.ok().body("Transaction with id " + transactionId + " was settled!");
        else
            return ResponseEntity.ok().body("Transaction with id " + transactionId + " was rejected!");
    }



}
