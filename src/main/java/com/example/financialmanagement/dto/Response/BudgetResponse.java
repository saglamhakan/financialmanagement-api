package com.example.financialmanagement.dto.Response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BudgetResponse  {

    private Long id;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserResponse user; // Burada UserResponse kullanılıyor

}
