package com.example.demoredis.controller;

import com.example.demoredis.entity.User;
import com.example.demoredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UseController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        boolean result = userService.saveUser(user);
        if(result)
            return ResponseEntity.ok("User created successfully!!!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> fetchAllUser(){
        List<User> users;
        users = userService.fetchAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable("id") Long id){
        User user;
        user = userService.fetchUserById(id);
        return ResponseEntity.ok(user);

    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        boolean result = userService.deleteUser(id);
        if(result)
            return ResponseEntity.ok("User delete successfully!!");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        User result = userService.fetchUserById(id);
        if(result== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.updateUser(id,user);
        return ResponseEntity.ok("update user successfully");
    }

    @GetMapping("/user/byName/{search}")
    public ResponseEntity<List<User>> fetchUserByFirstNameOrLastName(@PathVariable("search") String search){
        List<User> users;
        users = userService.fetchUserByListNameOrLastName(search);
        return ResponseEntity.ok(users);
    }
}
