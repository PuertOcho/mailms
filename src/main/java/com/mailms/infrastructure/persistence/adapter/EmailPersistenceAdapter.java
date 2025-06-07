package com.mailms.infrastructure.persistence.adapter;

import com.mailms.domain.model.Email;
import com.mailms.domain.port.out.EmailRepository;
import com.mailms.infrastructure.persistence.entity.EmailEntity;
import com.mailms.infrastructure.persistence.repository.JpaEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailPersistenceAdapter implements EmailRepository {

    private final JpaEmailRepository jpaEmailRepository;

    @Override
    public Email save(Email email) {
        EmailEntity entity = toEntity(email);
        EmailEntity savedEntity = jpaEmailRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Email> findByEmail(String email) {
        return jpaEmailRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaEmailRepository.existsByEmail(email);
    }

    private EmailEntity toEntity(Email email) {
        return EmailEntity.builder()
                .id(email.getId())
                .email(email.getEmail())
                .name(email.getName())
                .reason(email.getReason())
                .createdAt(email.getCreatedAt())
                .subscribed(email.isSubscribed())
                .build();
    }

    private Email toDomain(EmailEntity entity) {
        return Email.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .reason(entity.getReason())
                .createdAt(entity.getCreatedAt())
                .subscribed(entity.isSubscribed())
                .build();
    }
} 