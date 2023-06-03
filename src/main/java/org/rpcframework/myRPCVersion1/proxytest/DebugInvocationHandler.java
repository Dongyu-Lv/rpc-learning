package org.rpcframework.myRPCVersion1.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 00:13
 */
public class DebugInvocationHandler implements InvocationHandler {

    // 代理类的真实对象
    private Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 调用方法前，添加自己的操作
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        // 调用方法后，添加自己的操作
        System.out.println("before method " + method.getName());
        return result;
    }
}
