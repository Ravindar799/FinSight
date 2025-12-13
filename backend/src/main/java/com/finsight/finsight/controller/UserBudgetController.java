package com.finsight.finsight.controller;

import com.finsight.finsight.entity.Budget;
import com.finsight.finsight.service.BudgetService;
import com.finsight.finsight.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// user -> budget endpoints

//GET /{userId}/budgets
//POST /{userId}/budgets
//GET /{userId}/budgets/{budgetId}
//PATCH /{userId}/budgets/{budgetId}
//DELETE /{userId}/budgets/{budgetId}

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserBudgetController {
    private final UsersService usersService;
    private final BudgetService budgetService;

    @GetMapping("/{userId}/budgets")
    public ResponseEntity<List<Budget>> getBudgetsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(usersService.getBudgetsByUserId(userId));
    }

    @PostMapping("/{userId}/budgets")
    public ResponseEntity<Budget> addBudgetToUser(@PathVariable Long userId , @RequestBody Budget budget) {
        return ResponseEntity.ok(usersService.addBudgetToUser(userId, budget));
    }

    @GetMapping("/{userId}/budgets/{budgetId}")
    public ResponseEntity<Budget> getBudget(@PathVariable Long userId, @PathVariable Long budgetId) {
        return ResponseEntity.ok(budgetService.getBudgetByUserIdAndBudgetId(userId, budgetId));
    }

    @PatchMapping("/{userId}/budgets/{budgetId}")
    public void updateBudget(@PathVariable Long userId, @PathVariable Long budgetId, @RequestBody Budget budget) {
        budgetService.updateBudgetByUserIdAndBudgetId(userId, budgetId, budget);
    }

    @DeleteMapping("/{userId}/budgets/{budgetId}")
    public void deleteBudget(@PathVariable Long userId, @PathVariable Long budgetId) {
        budgetService.deleteBudget(userId, budgetId);
    }
}
