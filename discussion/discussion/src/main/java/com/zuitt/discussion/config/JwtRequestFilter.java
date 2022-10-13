package com.zuitt.discussion.config;

import com.zuitt.discussion.services.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {
/*
*   Contains the implementation logic for generating a JWT using the
*   method defined in the "JwtToken" Class
*
*
* */

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    private final JwtToken jwtTokenUtil;


    public JwtRequestFilter(JwtToken jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                    throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        Logger logger = Logger.getLogger(JwtRequestFilter.class);

        String username = null;
        String jwtToken = null;

        if(requestTokenHeader != null) {
            jwtToken = requestTokenHeader;

            try {

                username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException exc) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException exc) {
                System.out.println("JWT Token has expired!");
            }

        }else{
            logger.warn("JWT Token is incomplete...");

        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }
}
