rpc框架


总体架构


### 提交1

1.公共模块：编写和服务相关的接口和数据模型，需要把公共模块引入消费者和服务提供方

2.服务提供者：真正实现接口的模块，打印用户名称并且返回参数中的用户对象

3.服务消费者：调用服务

​	使用了JDK动态代理

4.web服务器：让服务提供者可以远程访问

5.本地服务注册器: 根据服务名获取到对应的实现类



## 提交2

定义了全局配置加载功能，让RPC框架轻松地从配置文件中读取配置，并且维护一个全局配置对象，便于框架快速获取一致的配置

## 提交3

增加了YAML文件的全局配置加载功能

## 提交4

增加接口mock，数字和字符串mock数据，及时服务提供方没有上线方法，也有这个方法默认的返回值

## 提交5

增加序列化器JSON、Hessian、Kryo的实现，同时增加读取用户配置来指定序列化器（读取到序列化器的类），并且增加用户可以自定义序列化器



## 提交6

利用etcd实现注册中心，功能包括注册服务，取消服务，服务发现，注册中心销毁



## 提交7

增加利用zoomkeeper实现注册中心的方式，功能包括注册服务，取消服务，服务发现，注册中心销毁



## 提交8

更改了RPC使用的协议，从HTTP改为了TCP，提高了传输的性能



## 提交9

增加了负载均衡的算法，包括轮询，随机和一致性hash
