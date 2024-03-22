package com.zlz.zrpc.registry;

import com.zlz.zrpc.config.RegistryConfig;
import com.zlz.zrpc.model.ServiceMetaInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 注册中心测试
 */
public class RegistryTest {
    final Registry registry = new EtcdRegistry();

    @Before
    public void init(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("http://localhost:2379");
        registry.init(registryConfig);
    }

    @Test
    public void register() throws Exception {
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("1.0");
        serviceMetaInfo.setServiceAddress("localhost:1235");
        registry.register(serviceMetaInfo);

        serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("1.0");
        serviceMetaInfo.setServiceAddress("localhost:1234");
        registry.register(serviceMetaInfo);

        serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("2.0");
        serviceMetaInfo.setServiceAddress("localhost:1234");
        registry.register(serviceMetaInfo);
    }

    @Test
    public void unRegister()  {
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();

        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("1.0");
        serviceMetaInfo.setServiceAddress("localhost:1234");
        registry.unRegister(serviceMetaInfo);

    }

    @Test
    public void serviceDiscovery(){
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName("myService");
        serviceMetaInfo.setServiceVersion("1.0");
        String serviceKey = serviceMetaInfo.getServiceKey();
        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceKey);
        Assert.assertNotNull(serviceMetaInfoList);
    }

    @Test
    public void heartBeat() throws Exception{
        //init 方法已经执行心跳检测
        register();
        //阻塞一分钟
        Thread.sleep(60*1000L);
    }
}
