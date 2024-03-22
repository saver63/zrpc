package com.zlz.zrpc.config;

import cn.hutool.core.util.StrUtil;
import com.zlz.zrpc.registry.EtcdRegistry;
import com.zlz.zrpc.serializer.SerializerKeys;
import lombok.Data;

@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "zlz-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8081;

    /**
     * 模拟调用
     */
    private boolean mock = false;

    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

    /**
     * 注册配置
     */
    private RegistryConfig registryConfig = new RegistryConfig() ;

}
