package com.example.bankprojectpwj.exceptions;

public class TransacationNotFoundException extends RuntimeException {
    public TransacationNotFoundException(int id) {
        super("Transaction with id " + id + " doesn't exist!");
    }
}
