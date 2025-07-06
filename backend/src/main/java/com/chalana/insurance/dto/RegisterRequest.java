package com.chalana.insurance.dto;

import com.chalana.insurance.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String userEmail;

    @NotBlank
    private String password;

    private Role role;
}
