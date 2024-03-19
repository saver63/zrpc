package com.zlz.example.consumer;

import cn.hutool.core.text.replacer.StrReplacer;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.yaml.YamlUtil;
import com.zlz.example.common.model.User;
import com.zlz.example.common.service.UserService;
import com.zlz.zrpc.config.RpcConfig;
import com.zlz.zrpc.proxy.ServiceProxyFactory;
import com.zlz.zrpc.utils.ConfigUtils;

import java.util.Map;

/**
 * 建议服务消费者示例
 */
public class ConsumerExample {

    public static void main(String[] args) {

        //测试配置文件读取
//        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
//        System.out.println(rpc);

        //加载YAML文件尾Map
        Map<String ,Object> yamlMap = YamlUtil.loadByPath("application.yaml");
        JSONObject jsonObject = JSONUtil.parseObj(yamlMap);

        //现在可以基于yamlMap进行操作或将其转换为特定对象
        System.out.println(jsonObject);

        RpcConfig bean = JSONUtil.toBean(jsonObject.getJSONObject("rpc"),RpcConfig.class);

        System.out.println(bean);
    }
}
