package com.zlz.example.consumer;

import cn.hutool.core.text.replacer.StrReplacer;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.yaml.YamlUtil;
import com.zlz.example.common.model.User;
import com.zlz.example.common.service.UserService;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.proxy.ServiceProxyFactory;
import com.zlz.zrpc.utils.ConfigUtils;

import java.util.Map;

/**
 * 建议服务消费者示例
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

        long number = userService.getNumber();
        System.out.println(number);

        String userName = userService.getName();
        System.out.println(userName);

    }
}
