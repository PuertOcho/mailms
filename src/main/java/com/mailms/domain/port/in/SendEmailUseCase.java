package com.mailms.domain.port.in;

public interface SendEmailUseCase {
    void send(String to, String subject, String body);
} 