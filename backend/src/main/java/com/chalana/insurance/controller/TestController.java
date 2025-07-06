package com.chalana.insurance.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/protected")
    public String securedEndpoint() {
        return "You are authenticated 🎉";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin-only")
    public String adminOnly() {
        return "Only ADMIN can see this";
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/customer-only")
    public String customerOnly() {
        return "Only CUSTOMER can see this";
    }

}
