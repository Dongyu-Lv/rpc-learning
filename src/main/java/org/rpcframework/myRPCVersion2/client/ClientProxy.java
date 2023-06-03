package org.rpcframework.myRPCVersion2.client;

import org.rpcframework.myRPCVersion2.common.RPCRequest;
import org.rpcframework.myRPCVersion2.common.RPCResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 15:58
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
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramsTypes(method.getParameterTypes())
                .params(args)
                .build();
        RPCResponse response = IOClient.sendRequest(host, port, request);

        return response.getData();
    }

    public <T>T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }
}
