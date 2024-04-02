package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.rejected_handler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Tamako
 * @Date:2024/4/1 17:03
 * @Description:
 * CallerRunsPolicy是ThreadPoolExecutor的一种拒绝策略，当线程池无法接受新任务时（因为它已经关闭或达到了饱和状态），
 * 这种策略不会抛弃新任务，也不会抛出异常。
 * 相反，它会让提交任务的线程自己执行这个任务。
 * 这种策略提供了一种简单的反馈控制机制，能够降低新任务的提交速度。
 */
public class CallerRunsPolicyExample {
    public static void main(String[] args) {
        // 创建线程池，这里为了演示，故意设置较小的队列容量
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // 核心线程数
                4, // 最大线程数
                100, // 空闲线程存活时间
                TimeUnit.MILLISECONDS, // 时间单位
                new ArrayBlockingQueue<>(2), // 任务队列
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        );

        // 提交6个任务到线程池，超出队列容量的任务将由提交任务的线程自行执行
        for (int i = 0; i < 7; i++) {
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
     * 执行任务: 4，线程名: pool-1-thread-3
     * 执行任务: 6，线程名: main //main线程提交的任务，所以main线程自己执行
     * 执行任务: 0，线程名: pool-1-thread-1
     * 执行任务: 5，线程名: pool-1-thread-4
     * 执行任务: 1，线程名: pool-1-thread-2
     * 执行任务: 3，线程名: pool-1-thread-1
     * 执行任务: 2，线程名: pool-1-thread-3
     * 所有任务执行完成。
     */
}
