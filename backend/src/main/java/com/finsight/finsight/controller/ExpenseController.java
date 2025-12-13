package com.finsight.finsight.controller;

import com.finsight.finsight.entity.Expense;
import com.finsight.finsight.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//POST /api/categories/{categoryId}/expenses
//GET  /api/categories/{categoryId}/expenses
//PUT  /api/expenses/{expenseId}
//DELETE /api/expenses/{expenseId}

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/user/{userId}")
    public List<Expense> getByUser(@PathVariable Long userId) {
        return expenseService.getExpensesByUser(userId);
    }

    @GetMapping("/budget/{budgetId}")
    public List<Expense> getByBudget(@PathVariable Long budgetId) {
        return expenseService.getExpensesByBudget(budgetId);
    }

    @GetMapping("/category/{categoryName}")
    public List<Expense> getByCategory(@PathVariable String categoryName) {
        return expenseService.getExpensesByCategory(categoryName);
    }

    @GetMapping("/payment-mode/{paymentMode}")
    public List<Expense> getByPaymentMode(@PathVariable String paymentMode) {
        return expenseService.getExpensesByPaymentMode(paymentMode);
    }

    @GetMapping("/period/{period}")
    public List<Expense> getByPeriod(@PathVariable String period) {
        return expenseService.getExpensesByBudgetPeriod(period);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Expense>> getExpenses(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long budgetId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String paymentMode,
            @RequestParam(required = false) String period)
    {
        // The @RequestParam(required = false) annotation is key:
        // If a parameter is missing in the URL, Spring sets the corresponding
        // Java argument (e.g., userId) to null.

        List<Expense> expenses = expenseService.getFilteredExpenses(
                userId,
                budgetId,
                categoryName,
                paymentMode,
                period
        );

        return ResponseEntity.ok(expenses);
    }
}
