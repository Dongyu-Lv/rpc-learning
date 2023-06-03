package org.rpcframework.myRPCVersion0.service;

import org.rpcframework.myRPCVersion0.common.User;

/**
 * @author Dongyu Lv
 * @create 2023-05-25 23:47
 */

// 客户端通过接口调用服务器提供的服务
public interface UserService {
    User getUserById(Integer id);
}
