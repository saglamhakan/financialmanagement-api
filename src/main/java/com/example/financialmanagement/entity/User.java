package com.example.financialmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity(name = "users")
@Table
public class User  {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String username; // ve diğer kullanıcı bilgileri

   @OneToMany(mappedBy = "user")
   private List<Budget> budgets;

   @OneToMany(mappedBy = "user")
   private List<Transaction> transactions;

}
