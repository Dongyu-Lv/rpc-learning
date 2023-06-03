package org.rpcframework.myRPCVersion2.client;

import org.rpcframework.myRPCVersion2.common.Blog;
import org.rpcframework.myRPCVersion2.common.User;
import org.rpcframework.myRPCVersion2.service.BlogService;
import org.rpcframework.myRPCVersion2.service.UserService;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 16:27
 */
public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8900);
        UserService userService = clientProxy.getProxy(UserService.class);

        User userById = userService.getUserById(10);
        System.out.println("从服务端得到的user：" + userById);

        User user = User.builder().id(100).username("张三").sex(true).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("向服务端插入数据：" + integer);

        BlogService blogService = clientProxy.getProxy(BlogService.class);

        Blog blog = blogService.getBlogById(10);
        System.out.println("从服务端得到的blog：" + blog);
    }
}
