//package com.ecommerce.productservice.controller;
//
//import com.ecommerce.productservice.entity.UserInfo;
//import com.ecommerce.productservice.service.UserInfoService;
//import com.netflix.discovery.converters.Auto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class UserInfoController {
//
//    @Autowired
//    private UserInfoService userInfoService;
//
//    @PostMapping("/new-user")
//    public String addNewUser(@RequestBody UserInfo userInfo){
//        return userInfoService.addUser(userInfo);
//    }
//}
