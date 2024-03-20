package com.zlz.example.consumer;

import com.zlz.example.common.model.User;
import com.zlz.example.common.service.UserService;
import com.zlz.zrpc.proxy.ServiceProxyFactory;

/**
 * 服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {

        //获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("zlz");

        //调用
        User newUser = userService.getUser(user);
        if (newUser != null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("user == null");
        }



        String userName = userService.getName();
        System.out.println(userName);

    }
}
