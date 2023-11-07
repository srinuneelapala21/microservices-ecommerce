package com.microservices.authservice.service;

import com.microservices.authservice.Utils.JwtUtils;
import com.microservices.authservice.dto.Token;
import com.microservices.authservice.entity.UserCredentials;
import com.microservices.authservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService {
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public void saveUser(UserCredentials userCredentials) {
        userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
        userCredentialRepository.save(userCredentials);
    }

    public String generateToken(String userName) {
        return jwtUtils.generateToken(userName);
    }

    public Token validateToken(String token){
        System.out.println("*************Iam Inside ValidateToken*********************88888");
        try {
           jwtUtils.validateToken(token);
            return new Token(true);
        }
        catch (Exception e){
            return new Token(false);
        }
    }
}
