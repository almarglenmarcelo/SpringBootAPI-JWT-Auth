package com.zuitt.discussion.controller;


import com.zuitt.discussion.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {



    @GetMapping("/users")
    public String getAllUsers(){
        return "All users has been retrieved|";
    }


    @GetMapping("/users/{userId}")
    public String getAllUsers(@PathVariable int userId){
        return "Viewing details of user " + userId;
    }

    @PostMapping("/users")
    public String addUser(@RequestBody User user){

        return "New User " + user.getUsername() + " has been added";

    }


    @PutMapping("/users/{userId}")
    public User getUser(@PathVariable int userId, @RequestBody User user){

        return user;
    }


    @DeleteMapping("/users/{userId}")
    public String deleteUser(@RequestHeader(value="Authorization") String name){

        if(name.isEmpty())
            return "Unauthorized access";

        return String.format("Posts from user %s has been deleted!", name);
    }



}
