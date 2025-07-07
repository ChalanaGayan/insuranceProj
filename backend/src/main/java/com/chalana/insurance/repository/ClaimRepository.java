package com.chalana.insurance.repository;

import com.chalana.insurance.model.Claim;
import com.chalana.insurance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByCustomer(User user);
}
