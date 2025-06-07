package com.mailms.infrastructure.persistence.repository;

import com.mailms.infrastructure.persistence.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaEmailRepository extends JpaRepository<EmailEntity, Long> {
    Optional<EmailEntity> findByEmail(String email);
    boolean existsByEmail(String email);
} 