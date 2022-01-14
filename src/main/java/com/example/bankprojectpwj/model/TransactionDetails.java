package com.example.bankprojectpwj.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_details_id")
    private int transactionDetailsId;
    @Column(name = "product_name")
    @NotEmpty(message = "Product name can't be empty!")
    @NotNull(message = "Product name can't be null!")
    private String productName;
    @Min(value = 1, message = "The quantity should be at least 1!")
    private int quantity;


    public TransactionDetails(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public TransactionDetails() {
    }

    public int getTransactionId() {
        return transactionDetailsId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionDetailsId = transactionId;
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
