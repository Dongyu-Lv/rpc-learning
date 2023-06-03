package org.rpcframework.myRPCVersion1.client;

import org.rpcframework.myRPCVersion1.common.RPCRequest;
import org.rpcframework.myRPCVersion1.common.RPCResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 00:59
 */
public class ClientProxy implements InvocationHandler {

    private String host;
    private int port;

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 注意：request构建过程中参数获取，使用到了Method类的方法
        RPCRequest request= RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        // 数据传输
        RPCResponse response = IOClient.sendRequest(host, port, request);

        return response.getData();
    }

    public <T>T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{clazz},
                this
        );
    }

}
