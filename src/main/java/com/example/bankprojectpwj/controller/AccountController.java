package com.example.bankprojectpwj.controller;

import com.example.bankprojectpwj.model.Account;
import com.example.bankprojectpwj.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/new")
    public ResponseEntity<Account> addAccount(@Valid @RequestBody Account account){
        return ResponseEntity.ok().body(accountService.saveAccount(account));
    }

    @GetMapping("/{id}")
    public Account getBankAccount(@PathVariable @Valid int id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Account>> retrieveAccounts() {
        return ResponseEntity.ok().body(accountService.retrieveAccounts());
    }

    @GetMapping("/byCustomerId")
    public ResponseEntity<List<Account>> getAcountByCustomerId(@RequestParam int customerId) {
        return ResponseEntity.ok().body(accountService.getAccountByCustomerId(customerId));
    }

    @PatchMapping("/deactivateAccount/{id}")
    public ResponseEntity<String> deactivateAccount(@PathVariable int id){
        accountService.deactivateAccount(id);
        return ResponseEntity.ok().body("Account with id " + id + " was deactivated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable(name = "id") int accountId){
        accountService.deleteAccountById(accountId);
        return ResponseEntity.ok().body("Account with id " + accountId + " was deleted!");
    }

    @PatchMapping("/addAmount/{accountId}")
    public ResponseEntity<String> addAmountToBalance(@PathVariable int accountId, @RequestParam double amount){
        accountService.addAmount(accountId, amount);
        return ResponseEntity.ok().body("Account updated!");
    }
}
