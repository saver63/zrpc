package com.zlz.zlzrpc.springboot.starter.bootstrap;

import com.zlz.zlzrpc.springboot.starter.annotation.EnableRpc;
import com.zlz.zrpc.RpcApplication;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.server.tcp.VertxTcpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Rpc框架启动
 */
@Slf4j
public class RpcInitBootstrap implements ImportBeanDefinitionRegistrar {

    /**
     * Spring 初始化时执行，初始化RPC框架
     *
     * @param importingClassMetaData
     * @param registry
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetaData, BeanDefinitionRegistry registry){
        //获取EnableRpc注解的属性值
        boolean needServer = (boolean) importingClassMetaData.getAnnotationAttributes(EnableRpc.class.getName()).get("needServer");
        //RPC框架初始化(配置和注册中心)
        RpcApplication.init();

        //全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        //启动服务器
        if (needServer){
            VertxTcpServer vertxTcpServer = new VertxTcpServer();
            vertxTcpServer.doStart(rpcConfig.getServerPort());

        }else {
            log.info("不启动 server");
        }
    }
}
