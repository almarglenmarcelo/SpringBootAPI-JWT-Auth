package com.zuitt.discussion.services.users;

import com.zuitt.discussion.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    Iterable<User> getUsers();
    ResponseEntity getUserById(int id);
    void saveUser(User user);
    ResponseEntity updateUser(int userId, User user);
    ResponseEntity deleteUserById(int userId);
}
