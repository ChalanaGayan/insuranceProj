package com.chalana.insurance.controller;

import com.chalana.insurance.dto.PolicyRequest;
import com.chalana.insurance.dto.PolicyResponse;
import com.chalana.insurance.service.CustomerPolicyService;
import com.chalana.insurance.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;
    private final CustomerPolicyService customerPolicyService;

    // Create a new policy (ADMIN, AGENT)
    @PreAuthorize("hasAnyRole('ADMIN', 'AGENT')")
    @PostMapping
    public PolicyResponse createPolicy(@Valid @RequestBody PolicyRequest request, Authentication auth) {
        return policyService.createPolicy(request, auth.getName());
    }

    // Public endpoint to get all policies
    @GetMapping
    public List<PolicyResponse> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/enroll/{id}")
    public ResponseEntity<?> enrollPolicy(@PathVariable Long id, Authentication auth) {
        customerPolicyService.enrollPolicy(auth.getName(), id);
        return ResponseEntity.ok("Policy enrolled successfully");
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/my")
    public List<PolicyResponse> myPolicies(Authentication auth) {
        return customerPolicyService.getCustomerPolicies(auth.getName())
                .stream()
                .map(cp -> {
                    var p = cp.getPolicy();
                    return new PolicyResponse(
                            p.getId(), p.getName(), p.getType(),
                            p.getCoverageAmount(), p.getPremium(),
                            p.getDurationMonths(), p.getDescription(),
                            p.getCreatedBy(), p.getCreatedAt()
                    );
                })
                .toList();
    }

}
