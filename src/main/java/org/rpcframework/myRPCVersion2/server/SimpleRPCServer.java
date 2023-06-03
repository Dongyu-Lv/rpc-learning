package org.rpcframework.myRPCVersion2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 18:35
 */
public class SimpleRPCServer implements RPCServer {
    private ServiceProvider serviceProvider;

    public SimpleRPCServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务端启动了...");

            for (; ; ) {
                Socket socket = serverSocket.accept();
                new Thread(new WorkThread(socket, serviceProvider)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败！");
        }
    }

    @Override
    public void stop() {

    }
}
