package com.finsight.finsight.controller;

import com.finsight.finsight.entity.Expense;
import com.finsight.finsight.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//GET /users/{userId}/expenses
//POST /users/{userId}/expenses
//GET /users/{userId}/expenses/{expenseId}
//PATCH /users/{userId}/expenses/{expenseId}
//DELETE /users/{userId}/expenses/{expenseId}

@RestController
@RequestMapping("/api/users/{userId}/expenses")
@RequiredArgsConstructor
public class UserExpensesController {

    private  final ExpenseService expenseService;

    @GetMapping()
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getExpensesByUserId(userId));
    }

    @PostMapping()
    public ResponseEntity<Expense> addExpenseToUser(@PathVariable Long userId, @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.addExpenseToUser(userId, expense));
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpensesByUserIdAndExpenseId(@PathVariable Long userId, @PathVariable Long expenseId) {
        return ResponseEntity.ok(expenseService.findExpensesByUserIdAndExpenseId(userId, expenseId));
    }

    @PatchMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long userId, @PathVariable Long expenseId, @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpense(userId, expenseId, expense));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Boolean> deleteExpense(@PathVariable Long userId, @PathVariable Long expenseId) {
        return ResponseEntity.ok(expenseService.deleteExpense(userId, expenseId));
    }
}
