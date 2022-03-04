package com.bjsxt.corehr.service.impl;

import com.bjsxt.corehr.mapper.AsyncTaskExecutorDaoImpl;
import com.bjsxt.corehr.utils.DateFormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @className: AsyncTaskExecutorServiceImpl
 * @description: 测试多线程
 * @author: RenDeYou
 * @date: 2021/4/20 16:35
 */
@Service
@Slf4j
public class AsyncTaskExecutorServiceImpl {

    @Autowired
    private AsyncTaskExecutorDaoImpl messageDao;

    /*以下方法测试多线程不使用注解*/
    /*以下方法测试多线程不使用注解*/
    /*以下方法测试多线程不使用注解*/

    /**
     * 无返回值
     */
    public void transTaskExecute(String str) {
        messageDao.getMessage(str);
        log.info("当前线程：{}   当前时间：{}", Thread.currentThread().getName(), DateFormatUtils.getNowDate());
    }

    /**
     * 有返回值
     * 使用Future接收返回值，主线程被迫等待
     */
    public String transTaskSubmit(String str) {
        String result = messageDao.getMessage(str);
        log.info("当前线程：{}   当前时间：{}", Thread.currentThread().getName(), DateFormatUtils.getNowDate());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*以下方法测试多线程使用注解*/
    /*以下方法测试多线程使用注解*/
    /*以下方法测试多线程使用注解*/

    /**
     * 无返回值
     */
    @Async("taskExecutor")
    public void transTask(String str) {
        messageDao.getMessage(str);
        log.info("当前线程：{}   当前时间：{}", Thread.currentThread().getName(), DateFormatUtils.getNowDate());
    }

    /**
     * 有返回值
     * 使用Future接收返回值，主线程被迫等待
     */
    @Async("taskExecutor")
    public Future<String> transTaskForFuture(String str) {
        String result = messageDao.getMessage(str);
        log.info("当前线程：{}   当前时间：{}", Thread.currentThread().getName(), DateFormatUtils.getNowDate());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            return AsyncResult.forExecutionException(e);
        }
        return AsyncResult.forValue(result);
    }

    /*以下方法测试多线程CompletableFuture不使用注解*/
    /*以下方法测试多线程CompletableFuture不使用注解*/
    /*以下方法测试多线程CompletableFuture不使用注解*/

    /**
     * 无返回值
     */
    public void transTaskForCompletableFutureRunAsync(String str) {
        messageDao.getMessage(str);
        log.info("当前线程：{}   当前时间：{}", Thread.currentThread().getName(), DateFormatUtils.getNowDate());
    }

    /**
     * 有返回值
     * 使用CompletableFuture接收返回值，主线程不用等待，whenComplete回调函数
     */
    public String transTaskForCompletableFutureSupplyAsync(String str) {
        String result = messageDao.getMessage(str);
        log.info("当前线程：{}   当前时间：{}", Thread.currentThread().getName(), DateFormatUtils.getNowDate());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*以下方法测试多线程ListenableFuture不使用注解*/
    /*以下方法测试多线程ListenableFuture不使用注解*/
    /*以下方法测试多线程ListenableFuture不使用注解*/

    /**
     * 无返回值（此处省略一万字）
     */

    /**
     * 有返回值
     * 使用listenableFuture获取子线程执行结果时，主线程不用等待，addCallback回调函数
     * 第三方谷歌提供，线程池用完必须关闭
     */
    public String transTaskForCallback(String str) {
        String result = messageDao.getMessage(str);
        log.info("当前线程：{}   当前时间：{}", Thread.currentThread().getName(), DateFormatUtils.getNowDate());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
