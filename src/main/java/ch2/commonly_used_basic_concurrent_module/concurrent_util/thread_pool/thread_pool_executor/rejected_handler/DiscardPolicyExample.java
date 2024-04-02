package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.rejected_handler;

/**
 * @Author:Tamako
 * @Date:2024/4/1 11:35
 * @Description:抛弃新提交的任务
 * 啥也没干
 * 使用DiscardPolicy拒绝策略可能会使得一些任务无声无息地丢失，因为它不会给出任何警告或异常。在实际应用中，应当根据具体需求慎重选择拒绝策略
 */
import java.util.concurrent.*;

public class DiscardPolicyExample {
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
                new ThreadPoolExecutor.DiscardPolicy() // 拒绝策略
        );

        // 提交20个任务到线程池，超出队列容量的任务将被丢弃，立即结束任务提交循环
        // 实际只能成功提交6个任务
        for (int i = 0; i < 20; i++) {
            int finalI = i;
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

        //关闭线程池
        executor.shutdown();
        try {
            // 等待所有任务完成
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完成。");
    }

    /**
     * output:
     * 执行任务: 0，线程名: pool-1-thread-1
     * 执行任务: 5，线程名: pool-1-thread-4
     * 执行任务: 4，线程名: pool-1-thread-3
     * 执行任务: 1，线程名: pool-1-thread-2
     * 执行任务: 2，线程名: pool-1-thread-1
     * 执行任务: 3，线程名: pool-1-thread-3
     * 所有任务执行完成。
     */
}
