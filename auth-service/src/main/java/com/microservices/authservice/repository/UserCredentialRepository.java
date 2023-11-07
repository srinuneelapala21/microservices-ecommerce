package com.microservices.authservice.repository;

import com.microservices.authservice.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserCredentialRepository extends JpaRepository<UserCredentials,Integer> {
    Optional<UserCredentials> findByUserName(String userName);
}
