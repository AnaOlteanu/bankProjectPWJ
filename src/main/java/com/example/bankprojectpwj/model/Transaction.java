package com.example.bankprojectpwj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;
    @JsonFormat(pattern="dd-MM-yyy")
    @NotNull
    private LocalDate date;
    @NotNull
    @Min(value = 0, message = "The amount can't be 0!")
    private double amount;
    private String status; //PENDING, SETTLED

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "transaction_details_id")
    @JsonIgnore
    private TransactionDetails transactionDetails;

    public Transaction(LocalDate date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Transaction() {
    }


    public void setTransactionId(int transationId) {
        this.transactionId = transationId;
    }

    public int getTransactionId() {
        return transactionId;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}
