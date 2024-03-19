package com.zlz.zrpc.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {

    /**
     * 启动服务
     *
     * @param port 端口
     */
    public void doStart(int port){
        //创建Vert.x实例
        Vertx vertx = Vertx.vertx();

        //创建HTTP服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        //绑定请求处理器，监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        //启动HTTP服务器并监听指定端口
        server.listen(port,result->{
           if (result.succeeded()){
               System.out.println("Server is now listening on port" + port);
           }else {
               System.out.println("Failed to start server: "+result.cause());
           }
        });
    }
}
