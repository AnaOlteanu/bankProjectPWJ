package com.example.bankprojectpwj.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TransactionRequest {
    @NotNull(message = "Account id must not be null!")
    private Integer accountId;
    @JsonFormat(pattern="dd-MM-yyy")
    @NotNull(message = "Date must not be null!")
    private LocalDate date;
    @Min(value = 1, message = "The amount can't be 0!")
    private double amount;
    @NotEmpty(message = "The product name should not be empty")
    private String productName;
    @Min(value = 1, message = "The quantity should be at least 1!")
    private int quantity;

    public TransactionRequest(Integer accountId, LocalDate date, double amount, String productName, int quantity) {
        this.accountId = accountId;
        this.date = date;
        this.amount = amount;
        this.productName = productName;
        this.quantity = quantity;
    }

    public TransactionRequest() {
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
