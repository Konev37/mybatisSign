package com.example.mybatisSign.controllers;

import com.example.mybatisSign.DAO.UserMapper;
import com.example.mybatisSign.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUser {
    @Autowired(required=false)
    UserMapper userMapper;

    @RequestMapping("/testUser")
    public User testUser(){
        return userMapper.GetUserByUsername("tom");
    };
}
