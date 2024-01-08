package com.example.financialmanagement.service;

import com.example.financialmanagement.Enum.TransactionType;
import com.example.financialmanagement.dto.Request.BudgetRequest;
import com.example.financialmanagement.dto.Response.BudgetResponse;
import com.example.financialmanagement.entity.Budget;
import com.example.financialmanagement.entity.Transaction;
import com.example.financialmanagement.repository.BudgetRepository;
import com.example.financialmanagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final ModelMapper modelMapper;
    private final TransactionRepository transactionRepository; // Transaction hesaplamaları için


    public BudgetService(BudgetRepository budgetRepository, ModelMapper modelMapper, TransactionRepository transactionRepository) {
        this.budgetRepository = budgetRepository;
        this.modelMapper = modelMapper;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public BudgetResponse createOrUpdateBudget(BudgetRequest budgetRequest) {
        Budget budget = modelMapper.map(budgetRequest, Budget.class);
        Budget savedBudget = budgetRepository.save(budget);
        return modelMapper.map(savedBudget, BudgetResponse.class);
    }

    @Transactional(readOnly = true)
    public BudgetResponse getBudget(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found with id: " + budgetId));
        return modelMapper.map(budget, BudgetResponse.class);
    }

    @Transactional
    public void deleteBudget(Long budgetId) {
        budgetRepository.deleteById(budgetId);
    }

    @Transactional(readOnly = true)
    public BigDecimal calculateRemainingBudget(Long userId, LocalDate date) {
        // Kullanıcının toplam bütçesini ve o aydaki toplam harcamalarını hesapla
        Budget budget = budgetRepository.findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(userId, date, date)
                .orElseThrow(() -> new RuntimeException("No budget found for user with ID: " + userId));
        BigDecimal totalBudget = budget.getAmount();

        BigDecimal totalExpense = transactionRepository
                .findByUserIdAndTransactionTypeAndTransactionDateBetween(
                        userId, TransactionType.EXPENSE, date.withDayOfMonth(1), date.withDayOfMonth(date.lengthOfMonth()))
                .stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalBudget.subtract(totalExpense);
    }

    // Diğer gereken metodlar...
}
