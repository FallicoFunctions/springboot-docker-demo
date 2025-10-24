package com.nickfallico.springboot_docker_demo.repository;

import com.nickfallico.springboot_docker_demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom queries can be added here if needed, e.g.:
    // Optional<Transaction> findByUserId(String userId);
}
