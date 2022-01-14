package com.example.bankprojectpwj.exceptions;

public class BankBranchNotFound extends RuntimeException{
    public BankBranchNotFound(int id) {
        super("Bank branch with id " + id + " does not exist!");
    }
}
