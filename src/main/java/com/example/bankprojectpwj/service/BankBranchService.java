package com.example.bankprojectpwj.service;

import com.example.bankprojectpwj.exceptions.BankBranchNotFound;
import com.example.bankprojectpwj.model.BankBranch;
import com.example.bankprojectpwj.repository.BankBranchRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BankBranchService {

    private final BankBranchRepository bankBranchRepository;

    public BankBranchService(BankBranchRepository bankBranchRepository) {
        this.bankBranchRepository = bankBranchRepository;
    }

    public BankBranch saveBankBranch(BankBranch bankBranch) {
            return bankBranchRepository.save(bankBranch);
        }

    public List<BankBranch> retrieveBankBranches() {
        return bankBranchRepository.findAll();
    }

    @Transactional
    public List<BankBranch> updateCityOfBankBranch(String currentCity, String newCity) {
        bankBranchRepository.updateCity(currentCity, newCity);
        List<BankBranch> bankBranches = bankBranchRepository.findBankBranchesByCity(newCity);
        return bankBranches;
    }

    public BankBranch retrieveBankbranchById(int id) {
        Optional<BankBranch> bankBranch = bankBranchRepository.findById(id);
        if(bankBranch.isPresent()){
            return bankBranch.get();
        } else {
            throw new BankBranchNotFound(id);
        }
    }
}
