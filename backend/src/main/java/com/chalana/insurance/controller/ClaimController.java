package com.chalana.insurance.controller;

import com.chalana.insurance.dto.ClaimRequest;
import com.chalana.insurance.dto.ClaimResponse;
import com.chalana.insurance.model.ClaimStatus;
import com.chalana.insurance.service.ClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    // CUSTOMER submits a claim
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<ClaimResponse> submitClaim(@Valid @RequestBody ClaimRequest request, Authentication auth) {
        return ResponseEntity.ok(claimService.createClaim(auth.getName(), request));
    }

    // CUSTOMER views their own claims
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/my")
    public ResponseEntity<List<ClaimResponse>> getMyClaims(Authentication auth) {
        return ResponseEntity.ok(claimService.getMyClaims(auth.getName()));
    }

    // ADMIN or AGENT can see all claims
    @PreAuthorize("hasAnyRole('ADMIN', 'AGENT')")
    @GetMapping
    public ResponseEntity<List<ClaimResponse>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @PreAuthorize("hasRole('ADMIN', 'AGENT')")
    @GetMapping("/{id}")
    public ResponseEntity<List<ClaimResponse>> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimsByUserId(id));
    }

    // ADMIN or AGENT updates claim status
    @PreAuthorize("hasAnyRole('ADMIN', 'AGENT')")
    @PutMapping("/{id}/status")
    public ResponseEntity<ClaimResponse> updateStatus(@PathVariable Long id, @RequestParam ClaimStatus status) {
        return ResponseEntity.ok(claimService.updateStatus(id, status));
    }
}
