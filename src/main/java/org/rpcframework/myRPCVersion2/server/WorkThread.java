package org.rpcframework.myRPCVersion2.server;

import org.rpcframework.myRPCVersion2.common.RPCRequest;
import org.rpcframework.myRPCVersion2.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 18:44
 */
public class WorkThread implements Runnable {
    Socket socket;
    ServiceProvider serviceProvider;

    public WorkThread(Socket socket, ServiceProvider serviceProvider) {
        this.socket = socket;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {

            // 读取客户端传过来的request
            RPCRequest request = (RPCRequest) ois.readObject();
            // 反射调用服务方法获得返回值
            RPCResponse response = getResponse(request);
            //写入到客户端
            oos.writeObject(response);
            oos.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("从IO中读取数据错误！");
        }
    }

    private RPCResponse getResponse(RPCRequest request) {
        // 获取服务名
        Object service = serviceProvider.getService(request.getInterfaceName());
        // 通过反射调用并执行方法
        Method method = null;
        try {
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(service, request.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("方法执行错误！");
            return RPCResponse.fail();
        }
    }
}