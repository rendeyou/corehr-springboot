package com.bjsxt.corehr.service.impl;

import com.google.common.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;

/**
 * @className: AsyncTaskExecutorServiceImplTest
 * @description: 测试多线程
 * @author: RenDeYou
 * @date: 2021/4/20 17:21
 */
@Slf4j
@SpringBootTest
class AsyncTaskExecutorServiceImplTest {

    @Autowired
    private AsyncTaskExecutorServiceImpl asyncTaskExecutorService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    /*以下方法测试多线程不使用注解*/
    /*以下方法测试多线程不使用注解*/
    /*以下方法测试多线程不使用注解*/

    /**
     * 无返回值
     */
    @Test
    public void transTaskExecute() {
        for (int i = 0; i < 10; i++) {
            try {
                int finalI = i;
                this.taskExecutor.execute(() -> {
                    asyncTaskExecutorService.transTaskExecute("你好" + finalI);
                });
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * 有返回值
     * 使用Future接收返回值，主线程被迫等待
     */
    @Test
    public void transTaskSubmit() {
        for (int i = 0; i < 10; i++) {
            try {
                int finalI = i;
                Future<String> future = this.taskExecutor.submit(() -> {
                    String str = asyncTaskExecutorService.transTaskSubmit("你好" + finalI);
                    return str;
                });
                /*以下代码是测试：主线程被迫等待*/
                while (true) {
                    if (future.isDone() && !future.isCancelled()) {
                        log.info("{}", "子线程执行完毕");
                        break;
                    } else {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("{}", Thread.currentThread().getName() + "主线程等待子线程执行完毕");
                    }
                }
                /*以上代码是测试：主线程被迫等待*/
                String str = null;
                try {
                    str = future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("子线程执行结果：{}", str);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /*以下方法测试多线程使用注解*/
    /*以下方法测试多线程使用注解*/
    /*以下方法测试多线程使用注解*/

    /**
     * 无返回值
     */
    @Test
    public void transTask() {
        for (int i = 0; i < 10; i++) {
            try {
                asyncTaskExecutorService.transTask("你好" + i);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * 有返回值
     * 使用Future接收返回值，主线程被迫等待
     */
    @Test
    public void transTaskForFuture() {
        for (int i = 0; i < 10; i++) {
            try {
                Future<String> future = asyncTaskExecutorService.transTaskForFuture("你好" + i);
                /*以下代码是测试：主线程被迫等待*/
                while (true) {
                    if (future.isDone() && !future.isCancelled()) {
                        log.info("{}", "子线程执行完毕");
                        break;
                    } else {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("{}", Thread.currentThread().getName() + "主线程等待子线程执行完毕");
                    }
                }
                /*以上代码是测试：主线程被迫等待*/
                String str = null;
                try {
                    str = future.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("子线程执行结果：{}", str);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /*以下方法测试多线程CompletableFuture不使用注解*/
    /*以下方法测试多线程CompletableFuture不使用注解*/
    /*以下方法测试多线程CompletableFuture不使用注解*/

    /**
     * 无返回值
     */
    @Test
    public void transTaskForCompletableFutureRunAsync() {
        for (int i = 0; i < 10; i++) {
            try {
                int finalI = i;
                CompletableFuture<Void> future = CompletableFuture
                        .runAsync(() -> {
                            asyncTaskExecutorService.transTaskForCompletableFutureRunAsync("你好" + finalI);
                        }, this.taskExecutor);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    /**
     * 有返回值
     * 使用CompletableFuture接收返回值，主线程不用等待，whenComplete回调函数
     */
    @Test
    public void transTaskForCompletableFutureSupplyAsync() {
        //指定结果集
        CompletableFuture<Void> completableFuture = null;
        final List<String> strings = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            try {
                int finalI = i;
                CompletableFuture<String> future = CompletableFuture
                        .supplyAsync(() -> {
                            String str = asyncTaskExecutorService.transTaskForCompletableFutureSupplyAsync("你好" + finalI);
                            return str;
                        }, this.taskExecutor)
                        .whenComplete(new BiConsumer<String, Throwable>() {
                            @Override
                            public void accept(String str, Throwable throwable) {
                                strings.add(str); //处理结果
                            }
                        });
                completableFuture = CompletableFuture.allOf(future); //处理线程
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        //阻塞主线程，等待所有子线程执行完毕
        completableFuture.join();
        //返回结果集
        strings.stream().forEach(e -> log.info("子线程执行结果：{}", e));
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
    //线程池中线程数量
    private static final int POOL_SIZE = 4;
    //带有回调机制的线程池
    private static final ListeningExecutorService EXECUTOR_SERVICE = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(POOL_SIZE));


    @Test
    public void transTaskForCallback() {
        //指定结果集
        final List<ListenableFuture<String>> futures = Collections.synchronizedList(new ArrayList<>());
        final List<String> strings = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            try {
                int finalI = i;
                ListenableFuture<String> future = EXECUTOR_SERVICE.submit(() -> {
                    String str = asyncTaskExecutorService.transTaskForCallback("你好" + finalI);
                    return str;
                });
                Futures.addCallback(future, new FutureCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        strings.add(s); //处理结果
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        log.info("子线程执行失败：{}", throwable.getMessage());
                    }
                });
                futures.add(future); //处理线程
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        //阻塞主线程，等待所有子线程执行完毕
        ListenableFuture<List<String>> listListenableFuture = Futures.allAsList(futures);
        try {
            listListenableFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //关闭线程池
        EXECUTOR_SERVICE.shutdownNow();
        //返回结果集
        strings.stream().forEach(e -> log.info("子线程执行结果：{}", e));
    }

}