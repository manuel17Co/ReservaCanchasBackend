package com.reservas.api.services;

import com.reservas.api.dto.AuthResponse;
import com.reservas.api.dto.LoginRequest;
import com.reservas.api.dto.LogoutResponse;
import com.reservas.api.dto.RegisterRequest;
import com.reservas.api.models.Usuario;
import com.reservas.api.repositories.UsuarioRepository;
import com.reservas.api.security.JwtUtil;
import com.reservas.api.security.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(passwordEncoder.encode(request.getContrasena()));

        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario.getCorreo());
        tokenBlacklistService.registerUserSession(usuario.getCorreo());
        return new AuthResponse(token, "Usuario registrado exitosamente");
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasena())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getCorreo());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        tokenBlacklistService.registerUserSession(userDetails.getUsername());

        return new AuthResponse(token, "Inicio de sesión exitoso");
    }

    public LogoutResponse logout(String token) {
        try {
            // Extraer el usuario del token
            String username = jwtUtil.extractUsername(token);

            // Agregar token a la blacklist
            tokenBlacklistService.addToBlacklist(token);

            // Cerrar la sesión del usuario
            tokenBlacklistService.closeUserSession(username);

            return new LogoutResponse("Sesión cerrada exitosamente", true);
        } catch (Exception e) {
            return new LogoutResponse("Error al cerrar sesión: " + e.getMessage(), false);
        }
    }
}
