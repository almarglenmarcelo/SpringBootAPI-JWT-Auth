package com.zuitt.discussion.repositories;

import com.zuitt.discussion.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Object> {


//    Custom Method to Find a User using a Username
    User findByUsername(String username);


}
