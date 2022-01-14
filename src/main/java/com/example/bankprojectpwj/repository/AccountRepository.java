package com.example.bankprojectpwj.repository;

import com.example.bankprojectpwj.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {


    @Modifying
    @Query(nativeQuery = true,
            value = "update account a set a.status = :status where a.account_id = :id")
    void changeAccountStatus(int id, String status);

    @Modifying
    @Query(nativeQuery = true,
            value = "update account a set a.balance = a.balance + :amount where account_id = :id")
    void updateBalance(double amount, int id);
}
