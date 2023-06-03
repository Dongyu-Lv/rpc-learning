package org.rpcframework.myRPCVersion1.proxytest;

import java.lang.reflect.Proxy;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 00:16
 */
public class JDKProxyFactory {
    public static Object getProxy(Object target) {
        // 获取代理对象
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载器
                target.getClass().getInterfaces(), // 代理需要实现的接口
                new DebugInvocationHandler(target) // 实现了InvocationHandle接口的对象
        );
    }
}
