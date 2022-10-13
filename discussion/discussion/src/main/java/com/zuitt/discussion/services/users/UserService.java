package com.zuitt.discussion.services.users;

import com.zuitt.discussion.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    Iterable<User> getUsers();

    ResponseEntity getUserById(int id);

    void saveUser(User user);

    ResponseEntity updateUser(int userId, User user);

    ResponseEntity deleteUserById(int userId);

//    Add an abstract method named findByUsername() that returns an optional
//    instance of the User class upon receiving a string
    Optional<User> findByUsername(String username);

}
