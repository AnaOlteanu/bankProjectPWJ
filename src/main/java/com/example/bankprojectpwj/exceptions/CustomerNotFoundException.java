package com.example.bankprojectpwj.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(int id) {
        super("Customer with id " + id + " does not exist!");
    }
}
