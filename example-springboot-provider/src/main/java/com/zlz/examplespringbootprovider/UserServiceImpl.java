package com.zlz.examplespringbootprovider;

import com.zlz.example.common.model.User;
import com.zlz.example.common.service.UserService;
import com.zlz.zlzrpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

@Service
@RpcService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
