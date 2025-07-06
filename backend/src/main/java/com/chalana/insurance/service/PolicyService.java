package com.chalana.insurance.service;

import com.chalana.insurance.dto.PolicyRequest;
import com.chalana.insurance.dto.PolicyResponse;
import com.chalana.insurance.model.Policy;
import com.chalana.insurance.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyResponse createPolicy(PolicyRequest request, String createdBy) {
        Policy policy = new Policy();
        policy.setName(request.getName());
        policy.setType(request.getType());
        policy.setCoverageAmount(request.getCoverageAmount());
        policy.setPremium(request.getPremium());
        policy.setDurationMonths(request.getDurationMonths());
        policy.setDescription(request.getDescription());
        policy.setCreatedBy(createdBy);

        Policy saved = policyRepository.save(policy);

        return mapToResponse(saved);
    }

    public List<PolicyResponse> getAllPolicies() {
        return policyRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PolicyResponse mapToResponse(Policy p) {
        return new PolicyResponse(
                p.getId(), p.getName(), p.getType(),
                p.getCoverageAmount(), p.getPremium(),
                p.getDurationMonths(), p.getDescription(),
                p.getCreatedBy(), p.getCreatedAt()
        );
    }
}
