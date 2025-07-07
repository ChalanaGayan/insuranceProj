package com.chalana.insurance.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_policies")
public class CustomerPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to customer
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;

    // Link to policy
    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    private LocalDateTime enrolledAt = LocalDateTime.now();
}
