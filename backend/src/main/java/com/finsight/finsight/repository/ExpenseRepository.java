package com.finsight.finsight.repository;

import com.finsight.finsight.entity.Budget;
import com.finsight.finsight.entity.Expense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findByIdAndUser_Id(Long expenseId, Long userId);
    Optional<Expense> findByIdAndBudget_Id(Long expenseId, Long budgetId);
    @Query("SELECT b FROM Expense b WHERE b.user.id = :userId AND b.id = :expenseId")
    Expense findByUserIdAndExpenseId(@Param("userId") Long userId, @Param("expenseId") Long expenseId);

    @Query("SELECT b FROM Expense b WHERE b.budget.id = :budgetId AND b.id = :expenseId")
    Expense findByBudgetIdAndExpenseId(@Param("budgetId") Long budgetId, @Param("expenseId") Long expenseId);

    @Modifying // Required for DML statements (INSERT, UPDATE, DELETE)
    @Transactional // Required to execute the modifying operation
    @Query("DELETE FROM Expense b WHERE b.id = :expenseId AND b.user.id = :userId")
    int deleteByIdAndUserId(@Param("expenseId") Long expenseId, @Param("userId") Long userId);

    @Modifying // Required for DML statements (INSERT, UPDATE, DELETE)
    @Transactional // Required to execute the modifying operation
    @Query("DELETE FROM Expense b WHERE b.id = :expenseId AND b.budget.id = :budgetId")
    int deleteByIdAndBudgetId(@Param("expenseId") Long expenseId, @Param("budgetId") Long budgetId);

    List<Expense> findByUserId(Long userId);

    List<Expense> findByBudgetId(Long budgetId);

    List<Expense> findByCategoryName(String categoryName);

    List<Expense> findByPaymentMode(String paymentMode);

    @Query("SELECT e FROM Expense e WHERE e.budget.period = :period")
    List<Expense> findByBudgetPeriod(@Param("period") String period);

    @Query("""
        SELECT e FROM Expense e
        LEFT JOIN e.user u
        LEFT JOIN e.budget b
        WHERE (:userId IS NULL OR u.id = :userId)
          AND (:budgetId IS NULL OR b.id = :budgetId)
          AND (:categoryName IS NULL OR e.categoryName = :categoryName)
          AND (:paymentMode IS NULL OR e.paymentMode = :paymentMode)
          AND (:period IS NULL OR b.period = :period)
    """)
    List<Expense> findFilteredExpenses(
            @Param("userId") Long userId,
            @Param("budgetId") Long budgetId,
            @Param("categoryName") String categoryName,
            @Param("paymentMode") String paymentMode,
            @Param("period") String period
    );

}
