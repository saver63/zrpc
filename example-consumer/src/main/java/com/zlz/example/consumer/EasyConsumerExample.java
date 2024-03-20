package com.zlz.example.consumer;

import com.zlz.example.common.model.User;
import com.zlz.example.common.service.UserService;
import com.zlz.zrpc.proxy.ServiceProxyFactory;

/**
 * 建议服务消费者示例
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        //todo 需要获取UserService的实现对象
        //静态代理
//        UserService userService = new UserServiceProxy();

        //动态代理
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
    }
}
