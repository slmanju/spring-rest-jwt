package com.manjula.security.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static com.manjula.security.config.Constants.*;

/**
 *  remembering who is logged in, but not in sessions.
 *  some sort of text (a token) that identifies the user.
 */
public class TokenBasedAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public TokenBasedAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    /**
     * After Spring finds the user by his/her username and verifies that the passwords match,
     * it calls the successfulAuthentication() method to store the user as logged in.
     * We override that method (from UsernamePasswordAuthenticationFilter)
     * in order to provide a token instead of a Session object.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authentication)
            throws IOException, ServletException {
        String token = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(((User) authentication.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_LIFETIME))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();

        response.addHeader(HttpHeaders.AUTHORIZATION, TOKEN_PREFIX + token);
    }

}
