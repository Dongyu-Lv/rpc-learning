package org.rpcframework.myRPCVersion1.client;

import org.rpcframework.myRPCVersion1.common.RPCRequest;
import org.rpcframework.myRPCVersion1.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 02:45
 */
public class IOClient {
    public static RPCResponse sendRequest(String host, int port, RPCRequest request) {
        try (Socket socket = new Socket(host, port);) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // 向服务器发送请求
            oos.writeObject(request);
            oos.flush();

            // 获取客户端传来的数据
            RPCResponse response = (RPCResponse) ois.readObject();

            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }
}
