package org.rpcframework.myRPCVersion0.server;

import org.rpcframework.myRPCVersion0.common.User;
import org.rpcframework.myRPCVersion0.service.UserService;

import java.util.Random;
import java.util.UUID;

/**
 * @author Dongyu Lv
 * @create 2023-05-25 23:50
 */

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(Integer id) {
        Random random = new Random();
        System.out.println("客户端访问了id为" + id + "的用户");
        // 模拟从数据库中查询用户信息
        User user = User.builder()
                .username(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean())
                .build();
        return user;

    }
}
