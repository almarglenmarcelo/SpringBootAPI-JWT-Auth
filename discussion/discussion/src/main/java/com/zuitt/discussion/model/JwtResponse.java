package com.zuitt.discussion.model;

import java.io.Serial;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -1050358235256787152L;

    private final String jwtToken;

    public JwtResponse (String jwtToken){
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
