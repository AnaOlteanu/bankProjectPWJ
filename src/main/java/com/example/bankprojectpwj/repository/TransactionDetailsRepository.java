package com.example.bankprojectpwj.repository;

import com.example.bankprojectpwj.model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {
}
