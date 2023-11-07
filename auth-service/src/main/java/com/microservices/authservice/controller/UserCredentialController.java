package com.microservices.authservice.controller;

import com.microservices.authservice.Utils.JwtUtils;
import com.microservices.authservice.dto.AuthDetails;
import com.microservices.authservice.dto.Token;
import com.microservices.authservice.entity.UserCredentials;
import com.microservices.authservice.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserCredentialController {

    @Autowired
    private UserCredentialService userCredentialsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserCredentials  userCredentials){
        userCredentialsService.saveUser(userCredentials);
        return "successfully Registered";
    }

    @PostMapping("/generate-token")
    public String generateToken(@RequestBody AuthDetails authDetails){
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDetails.getUserName(),authDetails.getPassword()));
        if(authentication.isAuthenticated()){
            return userCredentialsService.generateToken(authDetails.getUserName());
        }
        throw new UsernameNotFoundException("Access Denied");
    }


    @GetMapping("/validate-token")
    public Token validateToken(@RequestParam("token") String token){
        System.out.println("Iam inside the validate-token Endpoint");
        return userCredentialsService.validateToken(token);
    }
}
