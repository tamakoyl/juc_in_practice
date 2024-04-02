package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.scheduled;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Tamako
 * @Date:2024/3/30 16:32
 * @Description:延迟任务执行：适用于需要延迟一定时间后执行任务的场景，比如定时器功能、任务重试等
 * 具体例子：在系统启动后延迟一段时间执行初始化操作
 * 这种延迟任务执行适用于需要在系统启动后一段时间执行一些`必要的初始化操作`的情况
 */
public class DelayTaskExample {
    public static void main(String[] args) {
        // 创建 ScheduledThreadPoolExecutor
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // 延迟执行任务：延迟 5 秒后执行任务
        executor.schedule(new Task(), 5, TimeUnit.SECONDS);

        // 关闭线程池
        // executor.shutdown();

        ////////////////////////////////
        Timer timer = new Timer();
        long delay = 5000; // 5秒延迟
        timer.schedule(new InitializationTask(), delay);
        System.out.println("System started. Initializing...");
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            // 模拟延迟执行的任务
            System.out.println("Task is executing after a delay at: " + System.currentTimeMillis());
        }
    }

    static class InitializationTask extends TimerTask {
        @Override
        public void run() {
            // 执行初始化操作的逻辑
            System.out.println("Initialization completed.");
        }
    }
}
