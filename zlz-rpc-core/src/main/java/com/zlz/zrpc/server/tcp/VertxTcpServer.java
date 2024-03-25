package com.zlz.zrpc.server.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VertxTcpServer  {




    public void doStart(int port) {
        //构建Vert.x实例
        Vertx vertx = Vertx.vertx();

        //创建TCP服务器
        NetServer server = vertx.createNetServer();

        //处理请求
        server.connectHandler(new TcpServerHandler());
//        server.connectHandler(socket->{
//            //构造 parser
//            RecordParser parser = RecordParser.newFixed(8);
//            parser.setOutput(new Handler<Buffer>() {
//                //初始化
//                int size = -1;
//                //一次完整的读取(头+ 体)
//                Buffer resultBuffer = Buffer.buffer();
//                @Override
//                public void handle(Buffer buffer) {
//                    if (-1 == size){
//                        //读取消息体长度
//                        size = buffer.getInt(4);
//                        parser.fixedSizeMode(size);
//                        //写入头信息到结果
//                        resultBuffer.appendBuffer(buffer);
//                    }else {
//                        //写入体信息到结果
//                        resultBuffer.appendBuffer(buffer);
//                        System.out.println(resultBuffer.toString());
//                        //重置一轮
//                        parser.fixedSizeMode(8);
//                        size = -1;
//                        resultBuffer = Buffer.buffer();
//                    }
//                }
//            });
//
//            socket.handler(parser);
//
//
//            });

        //启动TCP服务器并监听指定端口
        server.listen(port, result->{
            if (result.succeeded()){
                System.out.println("TCP server started on port" + port);
            }else {
                System.err.println("Failed to start TCP server:" + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
