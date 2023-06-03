package org.rpcframework.myRPCVersion2.service;

import org.rpcframework.myRPCVersion2.common.Blog;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 15:47
 */
public interface BlogService {
    Blog getBlogById(Integer id);
}
