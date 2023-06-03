package org.rpcframework.myRPCVersion2.server;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 16:38
 */
// 把RPCServer抽象成接口，以后的服务端实现这个接口即可
public interface RPCServer {
    void start(int port);
    void stop();
}
