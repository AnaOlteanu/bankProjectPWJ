package com.example.bankprojectpwj.dto;

import javax.validation.constraints.NotNull;

public class CustomerAccountRequest {
    @NotNull(message = "Customer id must not be null!")
    private Integer customerId;
    @NotNull(message = "Account id must not be null!")
    private Integer accountId;
    @NotNull(message = "Holder must not be null!")
    private String isHolder;

    public CustomerAccountRequest() {
    }

    public CustomerAccountRequest(Integer customerId, Integer accountId, String isHolder) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.isHolder = isHolder;
    }

    public String getIsHolder() {
        return isHolder;
    }

    public void setIsHolder(String isHolder) {
        this.isHolder = isHolder;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }


}
