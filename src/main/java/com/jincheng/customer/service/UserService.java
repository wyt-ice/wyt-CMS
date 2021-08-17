package com.jincheng.customer.service;

import com.jincheng.customer.bean.User;
import com.jincheng.customer.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUser(String username) {
        User user = userMapper.queryUser(username);
        return user;
    }

    public void userRegister(String username, String email, String password) {
        userMapper.userRegister(username, email, password);
    }
}
