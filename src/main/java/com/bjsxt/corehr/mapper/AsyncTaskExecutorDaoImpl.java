package com.bjsxt.corehr.mapper;

import org.springframework.stereotype.Repository;

/**
 * @className: AsyncTaskExecutorDaoImpl
 * @description: 测试多线程
 * @author: RenDeYou
 * @date: 2021/4/20 16:45
 */
@Repository
public class AsyncTaskExecutorDaoImpl {

    public String getMessage(String str) {
        return str;
    }
}
