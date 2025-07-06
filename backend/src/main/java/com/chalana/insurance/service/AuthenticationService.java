package com.chalana.insurance.service;

import com.chalana.insurance.dto.LoginRequest;
import com.chalana.insurance.model.User;
import com.chalana.insurance.repository.UserRepository;
import com.chalana.insurance.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public String authenticate (LoginRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUserName(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return jwtService.generateToken(user);
    }

}
