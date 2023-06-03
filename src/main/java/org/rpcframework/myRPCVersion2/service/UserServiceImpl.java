package org.rpcframework.myRPCVersion2.service;

import org.rpcframework.myRPCVersion2.common.User;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 15:52
 */
public class UserServiceImpl implements UserService{
    @Override
    public User getUserById(Integer id) {
        User user = User.builder().id(id).username("zhangsan").sex(true).build();
        System.out.println("客户端查询了" + id + "用户");
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("插入数据成功：" + user);
        return 1;
    }
}
