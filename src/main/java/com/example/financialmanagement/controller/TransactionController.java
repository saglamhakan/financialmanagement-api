package com.example.financialmanagement.controller;

import com.example.financialmanagement.Enum.TransactionType;
import com.example.financialmanagement.dto.Request.TransactionRequest;
import com.example.financialmanagement.dto.Response.TransactionResponse;
import com.example.financialmanagement.service.TransactionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = transactionService.createTransaction(transactionRequest);
        return ResponseEntity.ok(transactionResponse);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactionsByUserAndDate(
            @RequestParam Long userId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);
        List<TransactionResponse> transactions = transactionService.getTransactionsByUserAndDate(userId, start, end);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/total-for-month")
    public ResponseEntity<BigDecimal> calculateTotalForMonth(
            @RequestParam Long userId,
            @RequestParam String month,
            @RequestParam TransactionType type) {
        YearMonth yearMonth = YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
        BigDecimal total = transactionService.calculateTotalForMonth(userId, yearMonth, type);
        return ResponseEntity.ok(total);
    }

    // Diğer gereken endpoint'ler...
}
