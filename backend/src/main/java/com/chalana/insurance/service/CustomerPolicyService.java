package com.chalana.insurance.service;


import com.chalana.insurance.model.CustomerPolicy;
import com.chalana.insurance.model.Policy;
import com.chalana.insurance.model.User;
import com.chalana.insurance.repository.CustomerPolicyRepository;
import com.chalana.insurance.repository.PolicyRepository;
import com.chalana.insurance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerPolicyService {

    private final CustomerPolicyRepository customerPolicyRepository;
    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;


    public void enrollPolicy(String username, Long policyID) {

        User customer = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Policy policy = policyRepository.findById(policyID)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        // Check if the customer is already enrolled in the policy
        if (customerPolicyRepository.existsByCustomerAndPolicy(customer, policy)) {
            throw new RuntimeException("Customer is already enrolled in this policy");
        }

        CustomerPolicy cp = new CustomerPolicy();
        cp.setCustomer(customer);
        cp.setPolicy(policy);

        customerPolicyRepository.save(cp);
    }

    public List<CustomerPolicy> getCustomerPolicies(String username) {
        User customer = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return customerPolicyRepository.findByCustomer(customer);
    }
}
