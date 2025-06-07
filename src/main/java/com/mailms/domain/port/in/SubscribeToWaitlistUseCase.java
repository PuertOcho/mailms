package com.mailms.domain.port.in;

import com.mailms.domain.model.Email;

public interface SubscribeToWaitlistUseCase {
    Email subscribe(String email);
    Email subscribe(String email, String name, String reason);
} 