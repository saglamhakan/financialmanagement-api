package com.example.financialmanagement.repository;

import com.example.financialmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
}
