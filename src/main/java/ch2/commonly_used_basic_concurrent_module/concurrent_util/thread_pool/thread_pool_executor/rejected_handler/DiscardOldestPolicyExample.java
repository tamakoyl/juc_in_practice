package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.rejected_handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Tamako
 * @Date:2024/4/1 12:01
 * @Description:
 * 当线程池达到其饱和状态（即线程池的所有线程都在忙，且工作队列已满）时，
 * 这种策略会丢弃工作队列中等待时间最长的任务，然后尝试重新提交当前任务。
 * 这种方式可以在高负载情况下保持较新的任务，牺牲较旧的任务。
 */
public class DiscardOldestPolicyExample {
    public static void main(String[] args) {
        // 创建线程池，这里为了演示，故意设置较小的队列容量
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // 核心线程数
                4, // 最大线程数
                100, // 空闲线程存活时间
                TimeUnit.MILLISECONDS, // 时间单位
                new ArrayBlockingQueue<>(2), // 任务队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.DiscardOldestPolicy() // 拒绝策略
        );

        // 提交6个任务到线程池(4+2)
        // 超出队列容量的任务将触发DiscardOldestPolicy
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    // 模拟任务执行时间
                    Thread.sleep(1000);
                    System.out.println("执行任务: " + finalI + "，线程名: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 再提交一个任务，这将导致队列中等待时间最长的任务被丢弃
        // 任务2最早进队列，所以被抛弃了
        executor.execute(() -> {
            System.out.println("新任务执行，线程名: " + Thread.currentThread().getName());
        });

        executor.shutdown();
        try {
            // 等待所有任务完成
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("所有任务执行完成。");
    }
    /**
     * 输出：
     * 执行任务: 5，线程名: pool-1-thread-4
     * 执行任务: 0，线程名: pool-1-thread-1
     * 新任务执行，线程名: pool-1-thread-1
     * 执行任务: 1，线程名: pool-1-thread-2
     * 执行任务: 4，线程名: pool-1-thread-3
     * 执行任务: 3，线程名: pool-1-thread-4
     * 所有任务执行完成。
     */
}
