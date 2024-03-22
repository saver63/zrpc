package com.zlz.example.provider;

import com.zlz.example.common.service.UserService;
import com.zlz.zrpc.RpcApplication;
import com.zlz.zrpc.config.RegistryConfig;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.constant.RpcConstant;
import com.zlz.zrpc.model.ServiceMetaInfo;
import com.zlz.zrpc.registry.LocalRegistry;
import com.zlz.zrpc.registry.Registry;
import com.zlz.zrpc.registry.RegistryFactory;
import com.zlz.zrpc.server.HttpServer;
import com.zlz.zrpc.server.VertxHttpServer;

/**
 * 服务提供者示例
 */
public class ProviderExample {

    public static void main(String[] args) {

        //RPC框架初始化
        RpcApplication.init();

        //注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName,UserServiceImpl.class);

        //注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //启动web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
