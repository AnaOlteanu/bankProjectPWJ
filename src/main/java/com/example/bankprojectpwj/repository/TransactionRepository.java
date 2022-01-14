package com.example.bankprojectpwj.repository;

import com.example.bankprojectpwj.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> getTransactionByStatusEquals(String status);

    @Modifying
    @Query(nativeQuery = true,
            value = "update transaction t set t.status = :status where t.transaction_id = :transactionId")
    void updateStatus(int transactionId, String status);
}
