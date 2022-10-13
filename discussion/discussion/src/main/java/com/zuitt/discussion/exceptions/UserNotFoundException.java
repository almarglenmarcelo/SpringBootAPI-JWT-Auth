package com.zuitt.discussion.exceptions;



import org.springframework.web.bind.annotation.RestControllerAdvice;

public class UserNotFoundException extends Exception {


    public UserNotFoundException (String message) {
        super(message);
    }


}
