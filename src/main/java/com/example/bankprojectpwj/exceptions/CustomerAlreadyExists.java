package com.example.bankprojectpwj.exceptions;

public class CustomerAlreadyExists extends RuntimeException {
    public CustomerAlreadyExists(String cnp) {
        super("Customer with cnp " + cnp + " already exists!");
    }
}
