package com.chalana.insurance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClaimRequest {
    @NotNull
    private Long policyId;

    @NotNull
    private String reason;

    @NotNull
    private BigDecimal claimAmount;
}
