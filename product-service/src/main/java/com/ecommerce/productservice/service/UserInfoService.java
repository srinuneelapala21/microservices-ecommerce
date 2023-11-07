//package com.ecommerce.productservice.service;
//
//import com.ecommerce.productservice.entity.UserInfo;
//import com.ecommerce.productservice.repository.UserInfoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserInfoService {
//
//    @Autowired
//    private UserInfoRepository userInfoRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public String addUser(UserInfo userInfo) {
//        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//        userInfoRepository.save(userInfo);
//        return "User Added Successfully";
//    }
//}
