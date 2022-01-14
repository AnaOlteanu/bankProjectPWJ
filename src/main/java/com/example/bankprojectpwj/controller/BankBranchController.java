package com.example.bankprojectpwj.controller;


import com.example.bankprojectpwj.model.BankBranch;
import com.example.bankprojectpwj.model.Customer;
import com.example.bankprojectpwj.service.BankBranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bankBranches")
public class BankBranchController {

    public final BankBranchService bankBranchService;

    public BankBranchController(BankBranchService bankBranchService) {
        this.bankBranchService = bankBranchService;
    }

    @PostMapping("/new")
    public ResponseEntity<BankBranch> addBankBranch(@RequestBody @Valid BankBranch bankBranch){
        BankBranch newBankBranch = bankBranchService.saveBankBranch(bankBranch);
        return ResponseEntity.created(URI.create("/bankBranches/" + newBankBranch.getBankBranchId()))
                .body(newBankBranch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankBranch> retrieveBankBranchById(@PathVariable int id){
        return ResponseEntity.ok().body(bankBranchService.retrieveBankbranchById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BankBranch>> retrieveBankBranches() {
        return ResponseEntity.ok().body(bankBranchService.retrieveBankBranches());
    }

    @PatchMapping("/updateCity")
    public ResponseEntity<List<BankBranch>> updateCityOfBankBranch(@RequestParam String currentCity,
                                                         @RequestParam String newCity){
        List<BankBranch> bankBranches = bankBranchService.updateCityOfBankBranch(currentCity, newCity);
        return ResponseEntity.ok().body(bankBranches);
    }
}
