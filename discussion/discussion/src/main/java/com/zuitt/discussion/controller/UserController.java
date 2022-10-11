package com.zuitt.discussion.controller;


import com.zuitt.discussion.model.User;
import com.zuitt.discussion.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        return userService.getUsers();
    }

    @PostMapping("/users")
    public String addUser(@RequestBody User user){
        userService.saveUser(user);
        return "User created Successfully";
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/users/{userId}")
    public String updateUserById(@PathVariable int userId, @RequestBody User user){
        userService.updateUser(userId, user);

        return "User updated successfully";
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId){
        return userService.deleteUserById(userId);
    }



}
