package com.example.bankprojectpwj.exceptions;

public class NoAccountsFoundForTheCustomer extends RuntimeException {
    public NoAccountsFoundForTheCustomer(int customerId) {
        super("Customer with id " + customerId + " has no accounts");
    }
}
