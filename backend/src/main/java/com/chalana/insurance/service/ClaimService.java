package com.chalana.insurance.service;

import com.chalana.insurance.dto.ClaimRequest;
import com.chalana.insurance.dto.ClaimResponse;
import com.chalana.insurance.model.*;
import com.chalana.insurance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;

    public ClaimResponse createClaim(String username, ClaimRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Policy policy = policyRepository.findById(request.getPolicyId())
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        Claim claim = new Claim();
        claim.setCustomer(user);
        claim.setPolicy(policy);
        claim.setClaimAmount(request.getClaimAmount());
        claim.setReason(request.getReason());
        claim.setStatus(ClaimStatus.PENDING);

        return map(claimRepository.save(claim));
    }

    public List<ClaimResponse> getMyClaims(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return claimRepository.findByCustomer(user).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public List<ClaimResponse> getAllClaims() {
        return claimRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public List<ClaimResponse> getClaimsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return claimRepository.findByCustomer(user).stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public ClaimResponse updateStatus(Long id, ClaimStatus status) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus(status);
        return map(claimRepository.save(claim));
    }

    private ClaimResponse map(Claim c) {
        return new ClaimResponse(
                c.getId(),
                c.getPolicy().getName(),
                c.getReason(),
                c.getClaimAmount(),
                c.getStatus(),
                c.getCreatedAt()
        );
    }
}
