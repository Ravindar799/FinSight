package com.finsight.finsight.service;

import com.finsight.finsight.entity.Budget;
import com.finsight.finsight.entity.Expense;
import com.finsight.finsight.repository.BudgetRepository;
import com.finsight.finsight.repository.ExpenseRepository;
import com.finsight.finsight.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final BudgetRepository budgetRepository;

    public List<Expense> getExpensesByUserId(Long userId) {
        return userRepository.findById(userId).get().getExpenses();
    }

    public Expense addExpenseToUser(Long userId, Expense expense) {
        expense.setUser(userRepository.findById(userId).orElseThrow());
        return expenseRepository.save(expense);
    }

    public Expense findExpensesByUserIdAndExpenseId(Long userId, Long expenseId) {
        return expenseRepository.findByIdAndUser_Id(expenseId, userId)
            .orElseThrow(() -> new RuntimeException(
                    String.format("Expense ID %d not found for User ID %d", expenseId, userId)
            ));
    }

    @Transactional
    public Expense updateExpense(Long userId, Long expenseId, Expense expense) {
        Expense existingExpense = expenseRepository.findByUserIdAndExpenseId(userId, expenseId);

       if(existingExpense != null) {
           if(expense.getAmount() != null) {
               existingExpense.setAmount(expense.getAmount());
           }
           if(expense.getCategoryName() != null) {
               existingExpense.setCategoryName(expense.getCategoryName());
           }
           if(expense.getPaymentMode() != null) {
               existingExpense.setPaymentMode(expense.getPaymentMode());
           }
           if(expense.getNotes() != null) {
               existingExpense.setNotes(expense.getNotes());
           }
       }
        assert existingExpense != null;
        return expenseRepository.save(existingExpense);
    }

    @Transactional
    public boolean deleteExpense(Long userId, Long expenseId) {

        int deletedCount = expenseRepository.deleteByIdAndUserId(expenseId, userId);

        if (deletedCount == 0) {
//            throw new RuntimeException("Budget not found for ID " + expenseId + " or does not belong to user " + userId);
            return false;
        }
        return true;
    }

    public List<Expense> getExpensesByBudgetId(Long budgetId) {
        return budgetRepository.findById(budgetId).get().getExpenses();
    }

    @Transactional
    public Expense addExpenseToBudget(Long budgetId, Expense expense) {

        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        if (budget.getTotalAmount().compareTo(expense.getAmount()) < 0) {
            throw new RuntimeException("Expense exceeds remaining budget");
        }

        budget.setTotalAmount(budget.getTotalAmount() - expense.getAmount());
        expense.setBudget(budget);
        expense.setUser(budget.getUser());

        return expenseRepository.save(expense);
    }


    public Expense getExpenseByBudgetIdAndExpenseId(Long budgetId, Long expenseId) {
        return expenseRepository.findByIdAndBudget_Id(expenseId, budgetId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Expense ID %d not found for User ID %d", expenseId, budgetId)
                ));
    }

    @Transactional
    public Expense updateExpenseByBudgetId(Long budgetId, Long expenseId, Expense updatedExpense) {

        Expense existingExpense = expenseRepository
                .findByBudgetIdAndExpenseId(budgetId, expenseId);

        Budget budget = existingExpense.getBudget();

        if (updatedExpense.getAmount() != null) {

            Double oldAmount = existingExpense.getAmount();
            Double newAmount = updatedExpense.getAmount();

            budget.setTotalAmount(budget.getTotalAmount() + oldAmount);

            if (budget.getTotalAmount() < newAmount) {
                throw new RuntimeException("Updated expense exceeds remaining budget");
            }

            budget.setTotalAmount(budget.getTotalAmount() - newAmount);

            existingExpense.setAmount(newAmount);
        }

        if (updatedExpense.getCategoryName() != null) {
            existingExpense.setCategoryName(updatedExpense.getCategoryName());
        }

        if (updatedExpense.getPaymentMode() != null) {
            existingExpense.setPaymentMode(updatedExpense.getPaymentMode());
        }

        if (updatedExpense.getNotes() != null) {
            existingExpense.setNotes(updatedExpense.getNotes());
        }

        if (updatedExpense.getDate() != null) {
            existingExpense.setDate(updatedExpense.getDate());
        }

        return expenseRepository.save(existingExpense);
    }



    @Transactional
    public boolean deleteExpenseByBudgetId(Long budgetId, Long expenseId) {

        Expense expense = expenseRepository
                .findByBudgetIdAndExpenseId(budgetId, expenseId);

        if (expense == null) {
            return false;
        }

        Budget budget = expense.getBudget();

        budget.setTotalAmount(
                budget.getTotalAmount() + expense.getAmount()
        );

        expenseRepository.delete(expense);
        return true;
    }

    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public List<Expense> getExpensesByBudget(Long budgetId) {
        return expenseRepository.findByBudgetId(budgetId);
    }

    public List<Expense> getExpensesByCategory(String categoryName) {
        return expenseRepository.findByCategoryName(categoryName);
    }

    public List<Expense> getExpensesByPaymentMode(String paymentMode) {
        return expenseRepository.findByPaymentMode(paymentMode);
    }

    public List<Expense> getExpensesByBudgetPeriod(String period) {
        return expenseRepository.findByBudgetPeriod(period);
    }

    public List<Expense> getFilteredExpenses(
            Long userId,
            Long budgetId,
            String categoryName,
            String paymentMode,
            String period)
    {
        // 1. (Optional) Apply any business rules or validation here before querying.
        //    For example: Ensure a user has permission to view the requested data.

        // 2. Call the repository method directly with the received (potentially null) parameters.
        //    The repository's JPQL query handles the 'NULL = ignore filter' logic.
        return expenseRepository.findFilteredExpenses(
                userId,
                budgetId,
                categoryName,
                paymentMode,
                period
        );

        // 3. (Optional) Transform the List<Expense> entity to a DTO before returning
        //    if you need to hide or reshape data.
    }

}
