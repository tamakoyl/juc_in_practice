package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Tamako
 * @Date:2024/3/30 16:34
 * @Description:周期性任务执行：适用于需要周期性执行任务的场景，比如定时数据统计、定时数据同步等。
 */
public class RecurringTaskExample {
    public static void main(String[] args) {
        // 创建 ScheduledThreadPoolExecutor
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // 周期性执行任务：每隔一段时间执行一次任务
        executor.scheduleWithFixedDelay(new Task(), 0, 5, TimeUnit.SECONDS);

        // 关闭线程池
        // executor.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            // 模拟周期性执行的任务
            System.out.println("Task is executing periodically at: " + System.currentTimeMillis());
        }
    }
}
