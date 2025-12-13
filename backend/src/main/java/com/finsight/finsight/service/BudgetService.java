package com.finsight.finsight.service;

import com.finsight.finsight.entity.Budget;
import com.finsight.finsight.entity.Users;
import com.finsight.finsight.repository.BudgetRepository;
import com.finsight.finsight.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    public Budget getBudgetByUserIdAndBudgetId(Long userId, Long budgetId) {
        return budgetRepository.findByUserIdAndBudgetId(userId,budgetId);
    }

    @Transactional
    public void updateBudgetByUserIdAndBudgetId(Long userId, Long budgetId, Budget budget) {
        Users user =  userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user Not found "));
        Budget existingBudget = budgetRepository.findById(budgetId).orElseThrow();
        if(user.getBudgets().contains(existingBudget)) {
            if(budget.getName() != null) {
                existingBudget.setName(budget.getName());
            }
            if(budget.getTotalAmount() != null) {
                existingBudget.setTotalAmount(budget.getTotalAmount());
            }
            if(budget.getPeriod() != null) {
                existingBudget.setPeriod(budget.getPeriod());
            }
        }
        budgetRepository.save(existingBudget);
    }


    @Transactional
    public void deleteBudget(Long userId, Long budgetId) {

        int deletedCount = budgetRepository.deleteByIdAndUserId(budgetId, userId);

        if (deletedCount == 0) {
            throw new RuntimeException("Budget not found for ID " + budgetId + " or does not belong to user " + userId);
        }
    }
}
