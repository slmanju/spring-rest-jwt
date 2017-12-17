package com.manjula.security.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                                            Authentication authResult)
            throws IOException, ServletException {
        String token = "success!";
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
    }

}
