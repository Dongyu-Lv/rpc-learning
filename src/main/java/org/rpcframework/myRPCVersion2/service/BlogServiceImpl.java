package org.rpcframework.myRPCVersion2.service;

import org.rpcframework.myRPCVersion2.common.Blog;

/**
 * @author Dongyu Lv
 * @create 2023-05-27 15:54
 */
public class BlogServiceImpl implements BlogService{
    @Override
    public Blog getBlogById(Integer id) {
        // 模拟数据库查询行为
        Blog blog = Blog.builder().id(id).userId(22).title("我的博客").build();
        System.out.println("客户端查询了：" + id + "博客");
        return blog;
    }
}
