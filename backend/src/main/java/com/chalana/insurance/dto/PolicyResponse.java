package com.chalana.insurance.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyResponse {
    private Long id;
    private String name;
    private String type;
    private BigDecimal coverageAmount;
    private BigDecimal premium;
    private Integer durationMonths;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
}
