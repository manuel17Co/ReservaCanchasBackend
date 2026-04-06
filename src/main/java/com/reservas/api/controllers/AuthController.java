package com.reservas.api.controllers;

import com.reservas.api.dto.AuthResponse;
import com.reservas.api.dto.LoginRequest;
import com.reservas.api.dto.LogoutResponse;
import com.reservas.api.dto.RegisterRequest;
import com.reservas.api.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Remover "Bearer "
        return ResponseEntity.ok(authService.logout(token));
    }
}

