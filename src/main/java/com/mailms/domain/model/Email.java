package com.mailms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private Long id;
    private String email;
    private String name;
    private String reason;
    private LocalDateTime createdAt;
    private boolean subscribed;
} 