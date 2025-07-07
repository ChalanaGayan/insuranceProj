package com.chalana.insurance.repository;

import com.chalana.insurance.model.CustomerPolicy;
import com.chalana.insurance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Long> {
    List<CustomerPolicy> findByCustomer(User user);
}
