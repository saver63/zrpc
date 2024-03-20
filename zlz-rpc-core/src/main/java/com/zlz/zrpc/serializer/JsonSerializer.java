package com.zlz.zrpc.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlz.zrpc.model.RpcRequest;
import com.zlz.zrpc.model.RpcResponse;

import java.io.IOException;

/**
 * Json序列化器
 *
 * @author zlz
 */
public class JsonSerializer implements Serializer{

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T obj) throws IOException {

        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> classType) throws IOException {

        T obj = OBJECT_MAPPER.readValue(bytes, classType);

        if (obj instanceof RpcRequest){
            return handleRequest((RpcRequest) obj, classType);
        }
        if (obj instanceof RpcResponse){
            return handleResponse((RpcResponse) obj, classType);
        }

        return obj;
    }

    /**
     * 由于Object的原始对象会被擦除，导致反序列化时会被作为LinkedHashMap 无法转换成原始对象，因此这里做了特殊处理
     *
     * @param rpcRequest
     * @param type
     * @return {@link T}
     * @param <T>
     * @throws IOException
     */
    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> type) throws IOException{

        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();

        //循环处理每个参数类型
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> clazz = parameterTypes[i];
            //如果类型不同，则需要重新处理一下类型
            if (!clazz.isAssignableFrom(args[i].getClass())){
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(argBytes, clazz);
            }
        }
        return type.cast(rpcRequest);
    }

    /**
     * 由于Object的原始对象会被擦除，导致反序列化时会被作为LinkedHashMap 无法转换成原始对象，因此这里做了特殊处理
     *
     * @param rpcResponse
     * @param type
     * @return {@link T}
     * @param <T>
     * @throws IOException
     */
    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> type) throws IOException{
        //处理响应数据
        byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes,rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }

}
