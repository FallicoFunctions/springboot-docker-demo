package com.nickfallico.springboot_docker_demo.repository;

import com.nickfallico.springboot_docker_demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom queries can be added here if needed, e.g.:
    // Optional<Transaction> findByUserId(String userId);

    @Query(value = "SELECT COALESCE(SUM(amount), 0) " +
            "FROM transaction " +
            "WHERE user_id = :userId " +
            "AND created_at >= CURDATE() " +
            "AND created_at < CURDATE() + INTERVAL 1 DAY",
            nativeQuery = true)
    BigDecimal getUserDailyTotal(@Param("userId") String userId);
}
