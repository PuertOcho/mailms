package com.mailms.domain.service;

import com.mailms.domain.model.Email;
import com.mailms.domain.port.in.SubscribeToWaitlistUseCase;
import com.mailms.domain.port.out.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscribeToWaitlistService implements SubscribeToWaitlistUseCase {

    private final EmailRepository emailRepository;

    @Override
    @Transactional
    public Email subscribe(String email) {
        return subscribe(email, null, null);
    }
    
    @Override
    @Transactional
    public Email subscribe(String email, String name, String reason) {
        if (emailRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already subscribed");
        }

        Email newEmail = Email.builder()
                .email(email)
                .name(name)
                .reason(reason)
                .createdAt(LocalDateTime.now())
                .subscribed(true)
                .build();

        return emailRepository.save(newEmail);
    }
} 