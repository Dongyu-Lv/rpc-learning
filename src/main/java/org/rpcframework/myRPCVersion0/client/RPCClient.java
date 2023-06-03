package org.rpcframework.myRPCVersion0.client;

import org.rpcframework.myRPCVersion0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * @author Dongyu Lv
 * @create 2023-05-26 00:38
 */

public class RPCClient {
    public static void main(String[] args) {
        // 1. 创建socket对象，并且指定服务器的地址和端口号
        try (Socket socket = new Socket("127.0.0.1", 8899);) {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            // 2. 通过输入流向服务端发送请求信息
            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();

            // 3. 通过输出流获取服务端相应信息
            User user = (User) objectInputStream.readObject();
            System.out.println("服务器返回的user：" + user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }
    }
}