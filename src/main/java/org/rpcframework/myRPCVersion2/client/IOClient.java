package org.rpcframework.myRPCVersion2.client;

import org.rpcframework.myRPCVersion2.common.RPCRequest;
import org.rpcframework.myRPCVersion2.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 16:04
 */
public class IOClient {
    public static RPCResponse sendRequest(String host, int port, RPCRequest request) {
        // 1. 创建socket对象，指定服务器ip和端口号
        try (Socket socket = new Socket(host, port);) {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // 2. 通过输出流向服务端发送请求
            System.out.println(request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            // 3. 通过输入流从客户端读取信息
            System.out.println("发送正常....");
            RPCResponse response = (RPCResponse) objectInputStream.readObject();

            return response;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
