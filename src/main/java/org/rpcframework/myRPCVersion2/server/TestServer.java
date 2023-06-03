package org.rpcframework.myRPCVersion2.server;

import org.rpcframework.myRPCVersion2.service.BlogService;
import org.rpcframework.myRPCVersion2.service.BlogServiceImpl;
import org.rpcframework.myRPCVersion2.service.UserService;
import org.rpcframework.myRPCVersion2.service.UserServiceImpl;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 19:31
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        //SimpleRPCServer simpleRPCServer = new SimpleRPCServer(serviceProvider);
        //simpleRPCServer.start(8899);

        RPCServer RPCServer = new ThreadPoolRPCServer(serviceProvider);
        RPCServer.start(8900);

    }
}
