package com.example.mybatisSign.services;

import com.example.mybatisSign.DAO.UserMapper;
import com.example.mybatisSign.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired(required=false)
    private UserMapper _UserMapper;

    public User GetUserByUsername(String username) {
        return this._UserMapper.GetUserByUsername(username);
    }

    public User GetUserByUsernameAndPassword(String username, String password) {
        return this._UserMapper.GetUserByUsernameAndPassowrd(username, password);
    }

    public void Save(User User) {
        this._UserMapper.Save(User.getUsername(), User.getPassword());
    }
}
