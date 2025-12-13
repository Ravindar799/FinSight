package com.finsight.finsight.controller;


import com.finsight.finsight.entity.Expense;
import com.finsight.finsight.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//GET /budgets/{budgetId}/expenses
//POST /budgets/{budgetId}/expenses
//GET /budgets/{budgetId}/expenses/{expenseId}
//PATCH /budgets/{budgetId}/expenses/{expenseId}
//DELETE /budgets/{budgetId}/expenses/{expenseId}

@RestController
@RequestMapping("api/budgets/{budgetId}/expenses")
@RequiredArgsConstructor
public class BudgetExpenseController {

    private final ExpenseService expenseService;

    @GetMapping()
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable Long budgetId) {
        return ResponseEntity.ok(expenseService.getExpensesByBudgetId(budgetId));
    }

    @PostMapping()
    public ResponseEntity<Expense> addExpenseToBudget(@PathVariable Long budgetId, @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.addExpenseToBudget(budgetId, expense));
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpenseByBudgetIdAndExpenseId(@PathVariable Long budgetId, @PathVariable Long expenseId) {
        return ResponseEntity.ok(expenseService.getExpenseByBudgetIdAndExpenseId(budgetId, expenseId));
    }

    @PatchMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long budgetId, @PathVariable Long expenseId, @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpenseByBudgetId(budgetId, expenseId, expense));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Boolean> deleteExpense(@PathVariable Long budgetId, @PathVariable Long expenseId) {
        return ResponseEntity.ok(expenseService.deleteExpenseByBudgetId(budgetId, expenseId));
    }


}
