package com.zuitt.discussion.services.users;

import com.zuitt.discussion.model.User;
import com.zuitt.discussion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public Iterable<User> getUsers(){
        return userRepository.findAll();

    }

    @Override
    public ResponseEntity getUserById(int id) {
        return new ResponseEntity(userRepository.findById(id), HttpStatus.OK);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public ResponseEntity updateUser(int userId, User user) {
        User userForUpdating = userRepository.findById(userId).get();

        userForUpdating.setUsername(user.getUsername());
        userForUpdating.setPassword(user.getPassword());

        userRepository.save(userForUpdating);

        return new ResponseEntity("User has been updated successfully!", HttpStatus.OK);

    }

    @Override
    public ResponseEntity deleteUserById(int userId) {

        Optional<User> userToDelete = userRepository.findById(userId);

        User user = null;

        if(userToDelete.isEmpty())
            throw new RuntimeException("No User Found!");
        else
            user = userToDelete.get();
            userRepository.deleteById(userId);



        return new ResponseEntity((String.format("The user %s has been deleted successfully!", user.getUsername())), HttpStatus.OK);
    }


}
