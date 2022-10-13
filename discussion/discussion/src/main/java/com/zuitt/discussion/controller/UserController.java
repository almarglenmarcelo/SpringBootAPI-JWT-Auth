package com.zuitt.discussion.controller;


import com.zuitt.discussion.exceptions.UserNotFoundException;
import com.zuitt.discussion.model.User;
import com.zuitt.discussion.services.users.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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


//    User Registration
    @RequestMapping(value="/users/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody Map<String, String> body) throws UserNotFoundException {
        String username = body.get("username");

        System.out.println(username);
        System.out.println("username");

        if(!userService.findByUsername(username).isEmpty()) {
            return new ResponseEntity<Object>("Username already exists!", HttpStatus.BAD_REQUEST);

        }else if(username.isEmpty()){
            return new ResponseEntity<Object>("Username should not be empty!", HttpStatus.BAD_REQUEST);
        }
        else {

            String password = body.get("password");
            String encodedPassword = new BCryptPasswordEncoder().encode(password);

            User newUser = new User(username, encodedPassword);

            userService.saveUser(newUser);

            return new ResponseEntity<>("User registered Successfully", HttpStatus.CREATED);


        }

    }



}
