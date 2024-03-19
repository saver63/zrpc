package com.zlz.example.provider;

import com.zlz.example.common.model.User;
import com.zlz.example.common.service.UserService;

/**
 * 用户服务实现类
 */
public class UserServiceImpl implements UserService {


    public User getUser(User user) {
        System.out.println("用户名: "+user.getName());
        return user;
    }
}
