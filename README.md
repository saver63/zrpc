rpc框架

![Snipaste_2024-03-19_09-39-14](E:\工作\work\网站\Snipaste_2024-03-19_09-39-14.png)

总体架构

![Snipaste_2024-03-19_09-45-46](E:\工作\work\网站\Snipaste_2024-03-19_09-45-46.png)

### 提交1

1.公共模块：编写和服务相关的接口和数据模型，需要把公共模块引入消费者和服务提供方

2.服务提供者：真正实现接口的模块，打印用户名称并且返回参数中的用户对象

3.服务消费者：调用服务

​	使用了JDK动态代理

4.web服务器：让服务提供者可以远程访问

5.本地服务注册器: 根据服务名获取到对应的实现类