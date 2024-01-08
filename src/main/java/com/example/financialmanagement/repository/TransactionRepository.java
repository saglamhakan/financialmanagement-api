package com.example.financialmanagement.repository;

import com.example.financialmanagement.Enum.TransactionType;
import com.example.financialmanagement.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdAndTransactionDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
    List<Transaction> findByUserIdAndTransactionTypeAndTransactionDateBetween(Long userId, TransactionType transactionType, LocalDate startDate, LocalDate endDate);


}

