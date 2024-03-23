package com.zlz.zrpc.server.tcp;


import com.zlz.zrpc.model.RpcRequest;
import com.zlz.zrpc.model.RpcResponse;
import com.zlz.zrpc.protocol.ProtocolMessage;
import com.zlz.zrpc.protocol.ProtocolMessageDecoder;
import com.zlz.zrpc.protocol.ProtocolMessageEncoder;
import com.zlz.zrpc.protocol.ProtocolMessageTypeEnum;
import com.zlz.zrpc.registry.LocalRegistry;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

import java.io.IOException;

import java.lang.reflect.Method;

/**
 * TCP请求处理器
 */
public class TcpServerHandler implements Handler<NetSocket> {

    /**
     * 处理请求
     *
     * @param scoket the event to handle
     */
    @Override
    public void handle(NetSocket socket) {
        TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
            //处理请求代码
        });
        socket.handler(bufferHandlerWrapper);

    }
}
