package com.zlz.example.consumer;

import com.zlz.example.common.model.User;
import com.zlz.example.common.service.UserService;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.proxy.ServiceProxyFactory;
import com.zlz.zrpc.utils.ConfigUtils;

/**
 * 建议服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {

        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpc);

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
