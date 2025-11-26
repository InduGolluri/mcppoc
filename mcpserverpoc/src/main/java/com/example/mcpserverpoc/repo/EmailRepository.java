package com.example.mcpserverpoc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mcpserverpoc.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
    long countBySender(String sender);
}
