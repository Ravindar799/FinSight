package com.finsight.finsight.service;

import com.finsight.finsight.entity.Budget;
import com.finsight.finsight.entity.Expense;
import com.finsight.finsight.entity.Users;
import com.finsight.finsight.repository.BudgetRepository;
import com.finsight.finsight.repository.ExpenseRepository;
import com.finsight.finsight.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public ResponseEntity<Users> registerUser(@RequestBody Users user) {

        Users savedUser = userRepository.save(user);

        for(Budget budget : user.getBudgets()) {
            budget.setUser(savedUser);
            budgetRepository.save(budget);
        }
        for(Budget budget : user.getBudgets()) {
            for(Expense expense : budget.getExpenses()) {
                expense.setBudget(budget);
                expense.setUser(user);
                expenseRepository.save(expense);
            }
        }
        for(Expense expense :user.getExpenses()) {
            expense.setUser(savedUser);
            expenseRepository.save(expense);
        }
        return ResponseEntity.ok(savedUser);
    }


    public Users getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean validateUser(String email, String pass) {
        Users user =  userRepository.findByEmail(email);
        return user.getPassword().equals(pass);
    }

    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findById(id).orElseThrow());
    }

    public List<Budget> getBudgetsByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow().getBudgets();
    }

    public Budget addBudgetToUser(Long userId, Budget budget) {
        Budget savedBgt = budgetRepository.save(budget);
        savedBgt.setUser(userRepository.findById(userId).orElseThrow());
        return savedBgt;
    }
}
