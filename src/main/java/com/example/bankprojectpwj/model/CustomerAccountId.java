package com.example.bankprojectpwj.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CustomerAccountId implements Serializable {

    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "account_id")
    private int accountId;

    public CustomerAccountId(int customerId, int accountId) {
        this.customerId = customerId;
        this.accountId = accountId;
    }

    public CustomerAccountId() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
