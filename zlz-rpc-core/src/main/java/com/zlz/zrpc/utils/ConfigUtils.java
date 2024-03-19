package com.zlz.zrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     *
     * @param tClass
     * @param prefix
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix){
        return loadConfig(tClass, prefix,"");
    }

    /**
     * 加载配置对象，支持区分环境
     *
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix,String environment){
        StringBuilder configFileeBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)){
            configFileeBuilder.append("-").append(environment);
        }
        configFileeBuilder.append(".properties");
        Props props = new Props(configFileeBuilder.toString());
        return props.toBean(tClass,prefix);
    }
}
