package com.zlz.zrpc.proxy;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * Mock服务代理
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {

    //共享变量
    private static final Faker faker = new Faker();

    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //根据方法的返回值类型，生成特定的默认值对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke{}", method.getName());

        return getDefaultObject(methodReturnType);
    }

    /**
     * 生成指定类型的默认值对象
     *
     * @param type
     * @return
     */
    private Object getDefaultObject(Class<?> type){
        //基本类型

        if (type.isPrimitive()){
            if (type == boolean.class){
                return false;
            }else if (type == char.class){
                return '\u0000';
            }else if (type == byte.class){
                return (byte) 0;
            }else if (type == float.class){
                return 0.0f;
            }else if (type == double.class){
                return 0.0d;
            }else if (type == short.class){
                return (short) 0;
            }else if (type == int.class){
                return 0;
            }else if (type == Long.class){
                return 0L;
            }
        } else {
            //为字符串类型提供伪造数据
            if (type == String.class){
                //伪造的(随机的)单词
                return faker.lorem().word();
            }
            //为日期类型提供伪造数据
            else if (type == java.util.Date.class){
                return faker.date().birthday();
            }
            // todo 添加更多对象类型的默认值生成逻辑
        }
        //对象类型
        return null;
    }
}
