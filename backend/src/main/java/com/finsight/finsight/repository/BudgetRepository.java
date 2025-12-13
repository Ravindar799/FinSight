package com.finsight.finsight.repository;

import com.finsight.finsight.entity.Budget;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findAllByUserId(Long userId);

    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId AND b.id = :budgetId")
    Budget findByUserIdAndBudgetId(@Param("userId") Long userId, @Param("budgetId") Long budgetId);

    @Modifying // Required for DML statements (INSERT, UPDATE, DELETE)
    @Transactional // Required to execute the modifying operation
    @Query("DELETE FROM Budget b WHERE b.id = :budgetId AND b.user.id = :userId")
    int deleteByIdAndUserId(@Param("budgetId") Long budgetId, @Param("userId") Long userId);
}
