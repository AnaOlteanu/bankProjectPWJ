package com.example.bankprojectpwj.exceptions;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(int id){
        super("Account with id " + id + " does not exist!");
    }
}
