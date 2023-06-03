package org.rpcframework.myRPCVersion1.server;

import org.rpcframework.myRPCVersion1.common.RPCRequest;
import org.rpcframework.myRPCVersion1.common.RPCResponse;
import org.rpcframework.myRPCVersion1.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 01:30
 */
public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        try (ServerSocket serverSocket = new ServerSocket(8900);){
            for (; ; ) {
                Socket socket = serverSocket.accept();
                ThreadFactory threadFactory = Executors.defaultThreadFactory();
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100), threadFactory);
                threadPoolExecutor.execute(() -> {
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    ) {
                        // 读取客户端传来的request
                        RPCRequest request = (RPCRequest) objectInputStream.readObject();
                        // 通过反射调用对应的方法
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                        Object invoke = method.invoke(userService, request.getParams());

                        // 封装response对象，传送给客户端
                        objectOutputStream.writeObject(RPCResponse.success(invoke));
                        objectOutputStream.flush();

                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        System.out.println("IO读取数据失败");

                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
