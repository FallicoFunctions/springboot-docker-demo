package com.nickfallico.springboot_docker_demo.controller;

import com.nickfallico.springboot_docker_demo.model.Transaction;
import com.nickfallico.springboot_docker_demo.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionRepository repo;
    public TransactionController(TransactionRepository repo) { this.repo = repo; }

    @PostMapping
    public Transaction create(@RequestBody Transaction t) {
        // simple fraud rule: reject > 10000
        if (t.getAmount() != null && t.getAmount() > 10000) {
            throw new IllegalArgumentException("Amount exceeds limit");
        }
        return repo.save(t);
    }

    @GetMapping("/{id}")
    public Transaction get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }
}
