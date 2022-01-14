package com.example.bankprojectpwj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_branch_id")
    private int BankBranchId;
    @NotNull(message = "Name should not be null!")
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotNull(message = "City should not be null")
    @NotEmpty(message = "City should not be empty")
    private String city;
    @NotNull(message = "Address should not be null")
    @NotEmpty(message = "Address should not be empty")
    private String address;

    @OneToMany(mappedBy = "bankBranch")
    @JsonIgnore
    private List<Customer> customerList = new ArrayList<>();

    public BankBranch(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public BankBranch() {
    }

    public int getBankBranchId() {
        return BankBranchId;
    }

    public void setBankBranchId(int bankBranchId) {
        BankBranchId = bankBranchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
