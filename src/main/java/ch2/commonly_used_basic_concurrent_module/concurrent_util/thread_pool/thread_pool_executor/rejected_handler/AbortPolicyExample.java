package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.rejected_handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author:Tamako
 * @Date:2024/3/30 18:43
 * @Description:
 * 1.抛出未检查的 RejectedExecutionException，调用者自己捕获处理
 *
 * public static class AbortPolicy implements RejectedExecutionHandler {
 *     public AbortPolicy() { }
 *
 *     public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
 *         throw new RejectedExecutionException("Task " + r.toString() +
 *                                              " rejected from " +
 *                                              e.toString()); // 抛异常！
 *     }
 * }
 * 2.这个是 ThreadPoolExecutor 的默认的 RejectedExecutionHandle handler，ThreadPoolExecutor 中有一个
 *      private static final RejectedExecutionHandler defaultHandler = new AbortPolicy();
 */
import java.util.concurrent.*;

public class AbortPolicyExample {
    public static void main(String[] args) {
        // 创建线程池，这里为了演示，故意设置较小的队列容量

        //线程池配置为核心线程数2，最大线程数4，队列容量2。这意味着线程池同时最多可以运行4个任务，并且有2个任务可以在队列中等待执行
        // 也就是完成6个任务
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // 核心线程数
                4, // 最大线程数
                100, // 空闲线程存活时间
                TimeUnit.MILLISECONDS, // 时间单位
                new ArrayBlockingQueue<>(2), // 任务队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );

        try {
            // 提交20个任务到线程池，超出队列容量的任务将抛出RejectedExecutionException异常，立即结束任务提交循环
            // 但实际只能完成6个任务
            for (int i = 0; i < 20; i++) {
                final int finalI = i;
                executor.execute(() -> {
                    try {
                        // 模拟任务执行时间
                        Thread.sleep(1000);
                        System.out.println("执行任务: " + finalI + "，线程名: " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (RejectedExecutionException e) {
            System.err.println("有任务被拒绝执行。");
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        try {
            // 等待所有任务完成
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完成。");
    }
}

