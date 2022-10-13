package com.zuitt.discussion.model;

import com.zuitt.discussion.config.JwtRequestFilter;

import java.io.Serial;
import java.io.Serializable;


public class JwtRequest implements Serializable {
    /*
     *   Model to be used in creating a JWT using the request Body
     *   contents for the payload in the "AuthController" File.
     *
     *
     * */

    @Serial
    private static final long serialVersionUID = 5856227066450085181L;

    private String username;
    private String password;


    public JwtRequest(){}


    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
