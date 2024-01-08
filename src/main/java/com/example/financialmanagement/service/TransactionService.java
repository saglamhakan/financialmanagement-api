package com.example.financialmanagement.service;

import com.example.financialmanagement.Enum.TransactionType;
import com.example.financialmanagement.dto.Request.TransactionRequest;
import com.example.financialmanagement.dto.Response.TransactionResponse;
import com.example.financialmanagement.entity.Transaction;
import com.example.financialmanagement.entity.User;
import com.example.financialmanagement.repository.TransactionRepository;
import com.example.financialmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        User user = userRepository.findById(transactionRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + transactionRequest.getUserId()));

        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        transaction.setUser(user);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return modelMapper.map(savedTransaction, TransactionResponse.class);
    }

    @Transactional(readOnly = true)
    public List<TransactionResponse> getTransactionsByUserAndDate(Long userId, LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepository.findByUserIdAndTransactionDateBetween(userId, startDate, endDate);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionResponse.class))
                .collect(Collectors.toList());
    }

    // Diğer gereken metodlar...

    @Transactional(readOnly = true)
    public BigDecimal calculateTotalForMonth(Long userId, YearMonth month, TransactionType type) {
        LocalDate startOfMonth = month.atDay(1);
        LocalDate endOfMonth = month.atEndOfMonth();

        return transactionRepository
                .findByUserIdAndTransactionTypeAndTransactionDateBetween(userId, type, startOfMonth, endOfMonth)
                .stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
