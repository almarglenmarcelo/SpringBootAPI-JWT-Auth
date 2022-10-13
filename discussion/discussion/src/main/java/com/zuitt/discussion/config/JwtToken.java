package com.zuitt.discussion.config;


import com.zuitt.discussion.model.User;
import com.zuitt.discussion.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;


//To generate our JSON Web Token (JWT)
@Component
public class JwtToken implements Serializable {


//    Taken from application.properties

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Serial
    private static final long serialVersionUID = -5134844513700711348L;

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60; // 5 HRS

//  Create Token
    private String doGenerateToken(Map<String, Object> claims, String subject) {
//         .setClaims() - includes the information to show the recipient which is the username
//         .setSubject() - adds the information about the subject
//         .setIssuedAt() - it sets the time and date that the token was created
//         .setExpiration() - it sets the expiration of the token
//         .signWith() -
//         .compact();

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();



    }

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();

        User user = userRepository.findByUsername(userDetails.getUsername());

        claims.put( "user", user.getId() );

        return doGenerateToken(claims, userDetails.getUsername());
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    public String getUsernameFromToken(String token) {

        String claim = getClaimFromToken(token, Claims::getSubject);

        return claim;
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }



    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }


    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);

        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails ){

        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }



//    Claims are a JWT's body and contain the information that the JWT creator wishes
//    to present to the JWT recipient

}
