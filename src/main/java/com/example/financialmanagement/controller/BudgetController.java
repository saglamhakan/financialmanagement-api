package com.example.financialmanagement.controller;

import com.example.financialmanagement.dto.Request.BudgetRequest;
import com.example.financialmanagement.dto.Response.BudgetResponse;
import com.example.financialmanagement.service.BudgetService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "http://localhost:4200")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<BudgetResponse> createOrUpdateBudget(@RequestBody BudgetRequest budgetRequest) {
        BudgetResponse budgetResponse = budgetService.createOrUpdateBudget(budgetRequest);
        return ResponseEntity.ok(budgetResponse);
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<BudgetResponse> getBudget(@PathVariable Long budgetId) {
        BudgetResponse budgetResponse = budgetService.getBudget(budgetId);
        return ResponseEntity.ok(budgetResponse);
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long budgetId) {
        budgetService.deleteBudget(budgetId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/remaining/{userId}/{date}")
    public ResponseEntity<BigDecimal> calculateRemainingBudget(@PathVariable Long userId, @PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        BigDecimal remainingBudget = budgetService.calculateRemainingBudget(userId, parsedDate);
        return ResponseEntity.ok(remainingBudget);
    }
}
