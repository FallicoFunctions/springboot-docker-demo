package com.nickfallico.springboot_docker_demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Entity
@Table(
        indexes = {
                @Index(name = "idx_userid_createdat", columnList = "userId, createdAt")
        }
)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(example = "u12345")
    @NotBlank
    private String userId;

    @Schema(example = "100.00")
    @NotNull
    @Min(1)
    private Double amount;

    @Schema(example = "USD")
    @NotBlank
    private String currency;

    private Instant createdAt = Instant.now();

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    @NotNull
    @Min(1)
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    @NotBlank
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}