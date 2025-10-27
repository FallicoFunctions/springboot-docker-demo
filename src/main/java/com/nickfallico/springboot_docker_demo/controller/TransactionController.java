package com.nickfallico.springboot_docker_demo.controller;

import com.nickfallico.springboot_docker_demo.model.Transaction;
import com.nickfallico.springboot_docker_demo.repository.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionRepository repo;
    public TransactionController(TransactionRepository repo) { this.repo = repo; }

    @PostMapping
    public Transaction create(@Valid @RequestBody Transaction t) {
        BigDecimal amount = t.getAmount() != null ? t.getAmount() : BigDecimal.ZERO;

        // rule 1: reject single tx > $10,000
        if (amount.compareTo(BigDecimal.valueOf(10_000)) > 0) {
            throw new IllegalArgumentException("Single transaction exceeds limit of 10,000");
        }

        // rule 2: reject if daily total > $20,000
        BigDecimal dailyTotal = repo.getUserDailyTotal(t.getUserId());
        if (dailyTotal.add(amount).compareTo(BigDecimal.valueOf(20_000)) > 0) {
            throw new IllegalArgumentException("Daily limit of 20,000 exceeded");
        }

        return repo.save(t);
    }

    @Operation(summary = "Get user's daily transaction total")
    @ApiResponse(responseCode = "200", description = "Daily total amount")
    @GetMapping("/daily-total/{userId}")
    public BigDecimal getUserDailyTotal(@PathVariable String userId) {
        return repo.getUserDailyTotal(userId);
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Transaction> getAll() {
        return repo.findAll();
    }
}