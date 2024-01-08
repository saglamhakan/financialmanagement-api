package com.example.financialmanagement.dto.Request;

import com.example.financialmanagement.Enum.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class TransactionRequest {

    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private String description;
    private Long userId; // Kullanıcının ID'si
}
