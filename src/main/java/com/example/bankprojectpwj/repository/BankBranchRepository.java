package com.example.bankprojectpwj.repository;

import com.example.bankprojectpwj.model.BankBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BankBranchRepository extends JpaRepository<BankBranch, Integer> {

    @Query("select b from BankBranch b where b.name = ?1")
    BankBranch findBankBranchByName(String bankBranchName);

    @Modifying
    @Query("update BankBranch b set b.city = ?2 where b.city = ?1")
    void updateCity(String currentCity, String newCity);

    List<BankBranch> findBankBranchesByCity(String city);
}
