package com.reservas.api.security;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {

    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();
    private final Set<String> userSessions = ConcurrentHashMap.newKeySet();

    /**
     * Agrega un token a la blacklist
     */
    public void addToBlacklist(String token) {
        blacklistedTokens.add(token);
    }

    /**
     * Verifica si un token está en la blacklist
     */
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    /**
     * Registra una sesión activa de usuario
     */
    public void registerUserSession(String username) {
        userSessions.add(username);
    }

    /**
     * Cierra la sesión de un usuario
     */
    public void closeUserSession(String username) {
        userSessions.remove(username);
    }

    /**
     * Verifica si el usuario tiene sesión activa
     */
    public boolean hasActiveSession(String username) {
        return userSessions.contains(username);
    }

    /**
     * Limpia la blacklist (útil para tokens expirados muy antiguos)
     */
    public void cleanupExpiredTokens() {
        // En producción, considera usar Redis con TTL automático
        blacklistedTokens.clear();
        userSessions.clear();
    }
}
