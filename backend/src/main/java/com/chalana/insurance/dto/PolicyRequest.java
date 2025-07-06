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

    @Positive
    @Min(1)
    @NotNull
    private BigDecimal coverageAmount;

    @Positive
    @Min(1)
    @NotNull
    private BigDecimal premium;

    @Positive
    @Min(1)
    @NotNull
    private Integer durationMonths;

    private String description;
}

