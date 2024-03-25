package com.zlz.examplespringbootconsumer;

import com.zlz.example.common.model.User;
import com.zlz.example.common.service.UserService;
import com.zlz.zlzrpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test(){
        User user = new User();
        user.setName("zlz");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}
