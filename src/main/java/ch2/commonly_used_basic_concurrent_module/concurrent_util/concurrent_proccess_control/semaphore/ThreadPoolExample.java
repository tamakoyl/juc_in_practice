package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author:Tamako
 * @Date:2024/3/25 18:56
 * @Description:
 * 在并发编程中，经常需要使用线程池来管理并发执行的任务。
 * Semaphore 可以用来限制线程池中同时执行的线程数量，防止过度消耗系统资源。
 */
public class ThreadPoolExample {
    private static final int MAX_THREADS = 5;
    private static final int TASKS_PER_THREAD = 10;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        Semaphore semaphore = new Semaphore(MAX_THREADS);

        for (int i = 0; i < TASKS_PER_THREAD * MAX_THREADS; i++) {
            executor.submit(() -> {
                try {
                    semaphore.acquire(); // 获取许可证
                    // 执行任务
                    System.out.println(Thread.currentThread().getName()+" is working");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(); // 释放许可证
                    System.out.println(Thread.currentThread().getName()+"  finished working");
                }
            });
        }

        executor.shutdown();
    }
}
