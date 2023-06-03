package org.rpcframework.myRPCVersion0.server;

import org.rpcframework.myRPCVersion0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author Dongyu Lv
 * @create 2023-05-26 00:47
 */
public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        // 1. 创建ServerSocket对象并绑定一个端口
        try (ServerSocket serverSocket = new ServerSocket(8899);) {
            for (; ; ) {
                System.out.println("等待连接中...");
                // 2. 通过accept() 方法监听客户端请求
                Socket socket = serverSocket.accept();
                System.out.println("连接到一个客户端");
                //new Thread(() -> {
                //    try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                //         ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());) {
                //
                //        // 3. 通过输入流读取客户端发送的请求信息
                //        int id = objectInputStream.readInt();
                //        User user = userService.getUserById(id);
                //        // 4. 通过输出流向客户端发送响应信息
                //        objectOutputStream.writeObject(user);
                //        objectOutputStream.flush();
                //    } catch (IOException e) {
                //        e.printStackTrace();
                //        System.out.println("IO读取数据失败");
                //    }
                //
                //}).start();

                ThreadFactory threadFactory = Executors.defaultThreadFactory();
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100), threadFactory);
                threadPoolExecutor.execute(() -> {
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    ) {
                        // 3. 读取客户端请求
                        int id = objectInputStream.readInt();
                        User user = userService.getUserById(id);

                        // 4. 通过输出流向客户端发送响应信息
                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("IO读取数据失败");
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }
}
