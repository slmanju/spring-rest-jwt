package com.manjula.security.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RestUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public RestUserDetailsService() {
    }

    public RestUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("manjula")) {
            throw new UsernameNotFoundException("not found");
        }
        return new User("manjula",
                passwordEncoder.encode("password"),
                Collections.singleton(new SimpleGrantedAuthority("ADMIN")));
    }

}
