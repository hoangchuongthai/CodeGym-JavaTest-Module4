package com.codegym.app6.repositories;

import com.codegym.app6.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("""
            SELECT p
            FROM Promotion p
            WHERE (:discountAmount IS NULL OR p.discountAmount = :discountAmount)
              AND (:startDate IS NULL OR p.startDate = :startDate)
              AND (:endDate IS NULL OR p.endDate = :endDate)
            ORDER BY p.startDate ASC, p.endDate ASC, p.id DESC
            """)
    List<Promotion> search(@Param("discountAmount") BigDecimal discountAmount,
                           @Param("startDate") LocalDate startDate,
                           @Param("endDate") LocalDate endDate);
}
