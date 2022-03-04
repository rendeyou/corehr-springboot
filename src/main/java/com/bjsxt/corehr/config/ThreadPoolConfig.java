package com.bjsxt.corehr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @className: ThreadPoolConfig
 * @description: 自定义线程池
 * @author: RenDeYou
 * @date: 2021/4/20 15:26
 */
@Configuration
@EnableAsync //支持异步
//@Async("taskExecutor") //开启异步（用在类或方法上）
public class ThreadPoolConfig {

    /**
     * IO密集型任务  = 一般为2*CPU核心数（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等）
     * CPU密集型任务 = 一般为CPU核心数+1（常出现于线程中：复杂算法）
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数量
        taskExecutor.setCorePoolSize(4);
        //最大线程数量（队列中任务数量站满后，最大线程数量开始工作）
        taskExecutor.setMaxPoolSize(30);
        //核心线程之外的线程在空闲后的最大存活时间（默认值60）
        taskExecutor.setKeepAliveSeconds(60);
        //队列中任务数量
        taskExecutor.setQueueCapacity(300);
        //当ThreadPool已经达到MaxPoolSize的时候，ThreadPool如何处理新任务
        //CallerRunsPolicy: 会在execute()方法的调用者（上级）的线程中运行被拒绝的任务，如果执行程序已关闭，则会抛弃该任务
        //AbortPolicy: 抛出java.util.concurrent.RejectedExecutionException异常
        //DiscardOldestPolicy: 抛弃旧的任务
        //DiscardPolicy: 抛弃当前的任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程名称前缀
        taskExecutor.setThreadNamePrefix("corehr-taskExecutor-");
        //核心线程一直存活
        taskExecutor.setAllowCoreThreadTimeOut(false);
        taskExecutor.initialize();
        return taskExecutor;
    }

}
