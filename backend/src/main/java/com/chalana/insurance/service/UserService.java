package com.chalana.insurance.service;

import com.chalana.insurance.model.Role;
import com.chalana.insurance.model.User;
import com.chalana.insurance.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(String userName, String userEmail, String password, Role role) {

        if (userRepository.existsByUsername(userName)) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(userEmail)) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(userName);
        user.setEmail(userEmail);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setEnabled(true);

        return userRepository.save(user);
    }


}
