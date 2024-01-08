package com.example.financialmanagement.dto.Response;

import com.example.financialmanagement.Enum.TransactionType;
import com.example.financialmanagement.util.BaseDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Data
public class TransactionResponse  {

    private Long id;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private String description;
    private UserResponse user; // Burada UserResponse kullanılıyor
}
