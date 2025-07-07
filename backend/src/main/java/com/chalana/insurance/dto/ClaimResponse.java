package com.chalana.insurance.dto;

import com.chalana.insurance.model.ClaimStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimResponse {
    private Long id;
    private String policyName;
    private String reason;
    private BigDecimal claimAmount;
    private ClaimStatus status;
    private LocalDateTime createdAt;
}
