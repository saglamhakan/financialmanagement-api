package com.example.financialmanagement.util;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class BaseDto {

    private Long id;
    private UUID uuid;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Date updatedDate;
}
