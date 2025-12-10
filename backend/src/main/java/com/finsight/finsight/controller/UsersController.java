package com.finsight.finsight.controller;

import com.finsight.finsight.entity.Users;
import com.finsight.finsight.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//GET /api/users
//POST /api/users/register
//POST /api/users/login
//GET  /api/users/{id}
//GET /api/users/email

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<Users> >getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Users>  getUserById(@PathVariable Long id) {
        Users user = usersService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email")
    public ResponseEntity<Users> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(usersService.getUserByEmail(email));
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUsers(@RequestBody Users user) {
        Users savedUser = usersService.registerUser(user).getBody();
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> isUserExists(@RequestBody Users user) {
        boolean valid = usersService.validateUser(user.getEmail(), user.getPassword());
        if (valid) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
