package com.example.bankprojectpwj.model;

import javax.persistence.*;

@Entity
@Table(name = "customer_account")
public class CustomerAccount {

    @EmbeddedId
    private CustomerAccountId customerAccountId;

    @Column(name = "is_account_holder")
    private String isAccountHolder;

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    public CustomerAccount(CustomerAccountId customerAccountId, String isAccountHolder, Customer customer, Account account) {
        this.customerAccountId = customerAccountId;
        this.isAccountHolder = isAccountHolder;
        this.customer = customer;
        this.account = account;
    }

    public CustomerAccount(CustomerAccountId customerAccountId, String isAccountHolder) {
        this.customerAccountId = customerAccountId;
        this.isAccountHolder = isAccountHolder;
    }

    public CustomerAccount() {
    }

    public CustomerAccountId getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(CustomerAccountId customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public String getIsAccountHolder() {
        return isAccountHolder;
    }

    public void setIsAccountHolder(String isAccountHolder) {
        this.isAccountHolder = isAccountHolder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
