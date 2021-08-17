package com.jincheng.customer.service;

import com.jincheng.customer.bean.User;
import com.jincheng.customer.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class registerService {
    @Autowired
    private UserMapper userMapper;

    public boolean isEmail(String email) {
//        System.out.println(email);
//        User user = userMapper.isEmail(email);
//        if (user == null) {
//            return true;
//        }
//        return false;
        return true;
    }

    public boolean isPassword(String password, String repassword) {
        if (password.equals(repassword)) {
            return true;
        }
        return false;
    }


}
