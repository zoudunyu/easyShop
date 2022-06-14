package com.yj.util;

import java.util.concurrent.*;

/**
 * 线程池工具类
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2020-02-19 13:25
 */
public class ThreadPoolUtil {

    /**
     * 构建线程池
     *
     * @param workSize
     * @return java.util.concurrent.ExecutorService
     * @author 邹敦宇
     * @date 2020-02-19 13:27:25
     **/
    public static ExecutorService buildPool(int workSize) {
        return buildPool(workSize / 4 + 1, workSize / 2 + 1, workSize);
    }

    /**
     * 构建线程池
     *
     * @param corePoolSize
     * @param maximumPoolSize
     * @return java.util.concurrent.ExecutorService
     * @author 邹敦宇
     * @date 2020-02-19 13:27:25
     **/
    public static ExecutorService buildPool(int corePoolSize, int maximumPoolSize) {
        return buildPool(corePoolSize, maximumPoolSize, Integer.MAX_VALUE);
    }

    /**
     * 构建线程池
     *
     * @param corePoolSize
     * @param maximumPoolSize
     * @param workSize
     * @return java.util.concurrent.ExecutorService
     * @author 邹敦宇
     * @date 2020-02-19 13:27:25
     **/
    public static ExecutorService buildPool(int corePoolSize, int maximumPoolSize, int workSize) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(workSize), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 关闭线程池, 等待所有线程完成
     *
     * @param executorService
     * @return
     * @author 邹敦宇
     * @date 2020-02-19 13:29:22
     **/
    public static void waitAndClose(ExecutorService executorService) {
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                //停歇一秒检测一次
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
