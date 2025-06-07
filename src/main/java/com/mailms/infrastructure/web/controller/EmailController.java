package com.mailms.infrastructure.web.controller;

import com.mailms.domain.model.Email;
import com.mailms.domain.port.in.SubscribeToWaitlistUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@RestController
@RequestMapping("/api/v1/emails")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {
    RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
    RequestMethod.DELETE, RequestMethod.OPTIONS})
public class EmailController {

    private final SubscribeToWaitlistUseCase subscribeToWaitlistUseCase;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @PostMapping("/waitlist")
    public ResponseEntity<?> subscribeToWaitlist(@RequestBody WaitlistRequest request) {
        String email = request.getEmail();
        String name = request.getName();
        String reason = request.getReason();
        
        // Validar que el email no sea nulo o vacío
        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El correo electrónico es obligatorio");
        }
        
        // Validar formato de email
        if (!isValidEmail(email)) {
            return ResponseEntity.badRequest().body("El formato del correo electrónico no es válido");
        }
        
        try {
            Email subscribedEmail = subscribeToWaitlistUseCase.subscribe(email, name, reason);
            return ResponseEntity.ok(subscribedEmail);
        } catch (IllegalArgumentException e) {
            // Si el email ya existe, devolvemos un error específico con código 409 (Conflict)
            if (e.getMessage().contains("already subscribed")) {
                return ResponseEntity.status(409).body("El correo electrónico ya está registrado en nuestra lista de espera");
            }
            // Para otros errores de argumentos ilegales
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Para otros errores inesperados
            return ResponseEntity.status(500).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
    
    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WaitlistRequest {
        private String email;
        private String name;
        private String reason;
    }
} 