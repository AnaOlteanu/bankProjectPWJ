package com.example.bankprojectpwj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "last_name")
    @NotNull(message = "Last name should not be null")
    @NotEmpty(message = "Last name should not be empty")
    private String lastName;
    @Column(name = "first_name")
    @NotNull(message = "First name should not be null")
    @NotEmpty(message = "First name should not be empty")
    private String firstName;
    @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")
    private String phone;
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(unique = true)
    @Pattern(regexp = "^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$")
    @NotNull(message = "Cnp should not be null")
    @NotEmpty(message = "Cnp should not be empty")
    private String cnp;
    private String status;

    @ManyToOne
    @JoinColumn(name = "bank_branch_id")
    private BankBranch bankBranch;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<CustomerAccount> customerAccounts = new ArrayList<>();

    public Customer(String lastName, String firstName, String phone, LocalDate dateOfBirth, String cnp) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.cnp = cnp;
    }

    public Customer() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }

    public List<CustomerAccount> getCustomerAccounts() {
        return customerAccounts;
    }

    public void setCustomerAccounts(List<CustomerAccount> customerAccounts) {
        this.customerAccounts = customerAccounts;
    }
}
