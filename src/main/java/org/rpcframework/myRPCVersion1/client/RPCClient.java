package org.rpcframework.myRPCVersion1.client;

import org.rpcframework.myRPCVersion1.common.User;
import org.rpcframework.myRPCVersion1.service.UserService;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 03:03
 */
public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8900);
        UserService proxy = clientProxy.getProxy(UserService.class);

        User user = proxy.getUserById(1);
        System.out.println("从服务端查询到的user：" + user);

        User zhangsan = User.builder().id(2).sex(true).username("zhangsan").build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("向服务器插入数据：" + integer);
    }
}
