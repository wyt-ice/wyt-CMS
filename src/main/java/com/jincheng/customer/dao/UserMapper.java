package com.jincheng.customer.dao;


import com.jincheng.customer.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {
    User queryUser(String username);

    void userRegister(String username, String email, String password);

    User isEmail(String email);
}
