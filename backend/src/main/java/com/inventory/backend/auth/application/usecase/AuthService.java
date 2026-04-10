package com.inventory.backend.auth.application.usecase;

import com.inventory.backend.auth.application.dto.AuthRequest;
import com.inventory.backend.auth.application.dto.AuthResponse;
import com.inventory.backend.auth.domain.model.User;
import com.inventory.backend.auth.domain.ports.out.UserRepositoryPort;
import com.inventory.backend.auth.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user.getUsername());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse register(AuthRequest request) {
        var user = new User(
                null,
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                "USER"
        );
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user.getUsername());
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
