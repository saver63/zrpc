package com.zlz.zrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import com.zlz.zrpc.RpcApplication;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.constant.RpcConstant;
import com.zlz.zrpc.loadbalancer.LoadBalancer;
import com.zlz.zrpc.loadbalancer.LoadBalancerFactory;
import com.zlz.zrpc.model.RpcRequest;
import com.zlz.zrpc.model.RpcResponse;
import com.zlz.zrpc.model.ServiceMetaInfo;
import com.zlz.zrpc.registry.Registry;
import com.zlz.zrpc.registry.RegistryFactory;
import com.zlz.zrpc.serializer.Serializer;
import com.zlz.zrpc.serializer.SerializerFactory;
import com.zlz.zrpc.server.tcp.VertxTcpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务代理（JDK动态代理）
 */
public class ServiceProxy implements InvocationHandler {

    /**
     *调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        //构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        try {

            //从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)){
                throw new RuntimeException("暂无服务地址");
            }
            //负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            //将调用方法名(请求路径)作为负载均衡的参数
            Map<String,Object> requestParams = new HashMap<>();
            requestParams.put("methodName",rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);

            //rpc请求
            RpcResponse rpcResponse = VertxTcpClient.doRequest(rpcRequest,selectedServiceMetaInfo);
            return rpcResponse.getData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
