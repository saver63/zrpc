package com.zlz.zlzrpc.springboot.starter.bootstrap;

import com.zlz.zlzrpc.springboot.starter.annotation.RpcService;
import com.zlz.zrpc.RpcApplication;
import com.zlz.zrpc.config.RegistryConfig;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.model.ServiceMetaInfo;
import com.zlz.zrpc.registry.LocalRegistry;
import com.zlz.zrpc.registry.Registry;
import com.zlz.zrpc.registry.RegistryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Rpc服务提供者启动
 */
@Slf4j
public class RpcProviderBootstrap implements BeanPostProcessor {

    /**
     * Bean初始化后执行，注册服务
     *
     * @param bean
     * @param beanName
     * @return
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        RpcService rpcSercvice = beanClass.getAnnotation(RpcService.class);
        if (rpcSercvice != null){
            //需要注册服务
            //1.获取服务基本信息
            Class<?> interfaceClass = rpcSercvice.interfaceClass();
            //默认值处理
            if (interfaceClass == void.class){
                interfaceClass = beanClass.getInterfaces()[0];
            }
            String serviceName = interfaceClass.getName();
            String serviceVersion = rpcSercvice.serviceVersion();

            //2.注册服务
            //本地服务
            LocalRegistry.register(serviceName, beanClass);

            //全局配置
            final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            //注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(serviceVersion);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + "服务注册失败", e);
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean,beanName);
    }
}
