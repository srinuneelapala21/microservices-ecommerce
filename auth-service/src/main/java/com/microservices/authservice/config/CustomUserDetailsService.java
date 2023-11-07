package com.microservices.authservice.config;

import com.microservices.authservice.entity.UserCredentials;
import com.microservices.authservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserCredentialRepository userCredentialRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> userCredentials = userCredentialRepository.findByUserName(username);
        return
                userCredentials
                        .map(CustomUserDetails::new)
                        .orElseThrow(() ->  new UsernameNotFoundException(username));
    }
}
