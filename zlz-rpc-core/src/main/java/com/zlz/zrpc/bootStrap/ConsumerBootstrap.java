package com.zlz.zrpc.bootStrap;


import com.zlz.zrpc.RpcApplication;


/**
 * 服务消费者示例
 */
public class ConsumerBootstrap {

    /**
     * 初始化
     */
    public static void init() {

        //RPC框架初始化(配置和注册中心)
        RpcApplication.init();


    }
}
