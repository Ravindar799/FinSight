package com.finsight.finsight.service;

import com.finsight.finsight.entity.Users;
import com.finsight.finsight.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Users> registerUser(@RequestBody Users user) {

        if (user.getBudgets() != null) {
            user.getBudgets().forEach(b -> b.setUser(user));
        }

        if (user.getCategories() != null) {
            user.getCategories().forEach(c -> c.setUser(user));
        }

        if (user.getExpenses() != null) {
            user.getExpenses().forEach(e -> e.setUser(user));
        }

        Users savedUser = userRepository.save(user);
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
}
