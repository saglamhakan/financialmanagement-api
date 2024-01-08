package com.example.financialmanagement.entity;

import com.example.financialmanagement.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity(name = "budget")
@Table
public class Budget  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount; // Bütçe miktarı

    private LocalDate startDate; // Bütçenin başlangıç tarihi

    private LocalDate endDate; // Bütçenin bitiş tarihi


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Bütçenin ait olduğu kullanıcı
}
