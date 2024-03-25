package com.zlz.zrpc.bootStrap;


import com.zlz.zrpc.RpcApplication;
import com.zlz.zrpc.config.RegistryConfig;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.model.ServiceMetaInfo;
import com.zlz.zrpc.model.ServiceRegisterInfo;
import com.zlz.zrpc.registry.LocalRegistry;
import com.zlz.zrpc.registry.Registry;
import com.zlz.zrpc.registry.RegistryFactory;
import com.zlz.zrpc.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * 服务提供者示例
 */
public class ProviderBootstrap {

    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {

        //RPC框架初始化(配置和注册中心)
        RpcApplication.init();

        //全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();


        //注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList){
            String serviceName = serviceRegisterInfo.getServiceName();
            //本地注册
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());

            //注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + "服务注册失败", e);
            }
        }

        //启动服务器
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
