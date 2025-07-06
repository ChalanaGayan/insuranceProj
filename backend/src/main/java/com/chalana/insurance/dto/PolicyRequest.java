package com.chalana.insurance.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PolicyRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotNull
    private BigDecimal coverageAmount;

    @NotNull
    private BigDecimal premium;

    @NotNull
    private Integer durationMonths;

    private String description;
}

