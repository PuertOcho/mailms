package com.mailms.domain.service;

import com.mailms.domain.port.in.SendEmailUseCase;
import com.mailms.domain.port.out.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmailService implements SendEmailUseCase {

    private final EmailSender emailSender;

    @Override
    public void send(String to, String subject, String body) {
        if (to == null || to.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination email is required");
        }
        if (subject == null) {
            subject = "";
        }
        if (body == null) {
            body = "";
        }
        emailSender.send(to, subject, body);
    }
} 