package com.zlz.zrpc.fault.tolerant;

import com.zlz.zrpc.model.RpcResponse;

import java.util.Map;

/**
 * 快速失败-容错策略(立刻通知外层调用方)
 */
public class FailFastTolerantStrategy implements TolerantStrategy{
    /**
     * 容错
     *
     * @param context 上下文，用于传输数据
     * @param e 异常
     * @return
     */
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务错误",e);
    }
}
