package com.zlz.zrpc.protocol;

import lombok.Getter;

/**
 * 协议消息类型枚举
 *
 * @author zlz
 */
@Getter
public enum ProtocolMessageTypeEnum {

    REQUEST(0),
    RESPONSE(1),
    HEART_BEAT(2),
    OTHERS(3);

    private final int key;
    ProtocolMessageTypeEnum(int key){
        this.key = key;
    }

    /**
     * 根据key获取枚举
     *
     * @param key
     * @return
     */
    public static ProtocolMessageTypeEnum getEnumByKey(int key){
        for (ProtocolMessageTypeEnum anEnum : ProtocolMessageTypeEnum.values()){
            if (anEnum.key == key){
                return anEnum;
            }
        }
        return null;
    }
}
