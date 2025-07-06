package com.chalana.insurance.controller;

import com.chalana.insurance.dto.AuthResponse;
import com.chalana.insurance.dto.LoginRequest;
import com.chalana.insurance.dto.RegisterRequest;
import com.chalana.insurance.model.Role;
import com.chalana.insurance.model.User;
import com.chalana.insurance.service.AuthenticationService;
import com.chalana.insurance.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            Role role = (request.getRole() == null) ? Role.CUSTOMER : request.getRole();
            User user = userService.registerUser(
                    request.getUserName(),
                    request.getUserEmail(),
                    request.getPassword(),
                    role
            );
            return ResponseEntity.ok("User registered successfully: " + user.getRole() + " " + user.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error registering user: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            String token = authenticationService.authenticate(request);
            return ResponseEntity.ok(new AuthResponse(
                    "Login successful",
                    token
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new AuthResponse(
                    "Login failed: " + e.getMessage(),
                    null
            ));
        }
    }

}
