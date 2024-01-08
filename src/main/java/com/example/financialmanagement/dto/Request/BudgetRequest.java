package com.example.financialmanagement.dto.Request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BudgetRequest {

    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId; // Kullanıcının ID'si
}
