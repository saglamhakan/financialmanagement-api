package com.example.financialmanagement.entity;

import com.example.financialmanagement.Enum.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity(name = "transaction")
@Table
public class Transaction  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; // İşlem miktarı

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // İşlem tipi (INCOME veya EXPENSE)

    private LocalDate transactionDate; // İşlem tarihi

    private String description; // İşlem açıklaması

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // İşlemi yapan kullanıcı


}
