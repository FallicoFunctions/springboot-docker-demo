package com.nickfallico.springboot_docker_demo.controller;

import com.nickfallico.springboot_docker_demo.model.Transaction;
import com.nickfallico.springboot_docker_demo.repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionRepository repo;
    public TransactionController(TransactionRepository repo) { this.repo = repo; }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Transaction t) {
        if (t.getAmount() > 10000) {
            return ResponseEntity.badRequest().body("Amount exceeds fraud threshold (10000)");
        }
        return ResponseEntity.ok(repo.save(t));
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
