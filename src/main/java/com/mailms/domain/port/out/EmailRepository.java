package com.mailms.domain.port.out;

import com.mailms.domain.model.Email;
import java.util.Optional;

public interface EmailRepository {
    Email save(Email email);
    Optional<Email> findByEmail(String email);
    boolean existsByEmail(String email);
} 