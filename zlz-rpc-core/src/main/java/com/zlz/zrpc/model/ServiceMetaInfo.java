package com.zlz.zrpc.model;

import cn.hutool.core.util.StrUtil;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.constant.RpcConstant;
import lombok.Data;

/**
 * 服务元信息(注册信息)
 */
@Data
public class ServiceMetaInfo {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本号
     */
    private String serviceVersion = RpcConstant.DEFAULT_SERVICE_VERSION;


    /**
     * 服务分组(暂未实现)
     */
    private String serviceGroup = "default";


    /**
     * 服务地址
     */
    private String serviceAddress;




    /**
     * 获取服务键名
     *
     * @return
     */
    public String getServiceKey(){
        //后续可扩展服务分组
//        return String.format("%s:%s:%s",serviceName,serviceVersion,serviceGroup);
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * 获取服务器注册键名
     *
     * @return
     */
    public String getServiceNodeKey(){
        return String.format("%s/%s", getServiceKey(),serviceAddress);
    }


}
