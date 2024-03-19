package com.zlz.example.provider;

import com.zlz.example.common.service.UserService;
import com.zlz.zrpc.registry.LocalRegistry;
import com.zlz.zrpc.server.HttpServer;
import com.zlz.zrpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {

        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        //启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
