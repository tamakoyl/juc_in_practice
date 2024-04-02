package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Author:Tamako
 * @Date:2024/3/25 18:26
 * @Description:限制并发数量
 */

public class SemaphoreExample {
    // 假设有5个许可证，表示最多同时允许5个线程访问共享资源
    private static final int MAX_AVAILABLE = 5;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(MAX_AVAILABLE);

        // 创建多个线程访问共享资源
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(semaphore, i)).start();
        }
    }

    static class Task implements Runnable {
        private final Semaphore semaphore;
        private final int taskId;

        Task(Semaphore semaphore, int taskId) {
            this.semaphore = semaphore;
            this.taskId = taskId;
        }

        @Override
        public void run() {
            try {
                System.out.println("Task " + taskId + " is waiting to access the shared resource.");
                semaphore.acquire(); // 请求许可证，如果许可证不可用，则阻塞线程直到获取许可证为止

                // 模拟访问共享资源的操作
                System.out.println("Task " + taskId + " is accessing the shared resource.");
                Thread.sleep(2000); // 模拟访问共享资源的耗时操作

                // 释放许可证，让其他等待的线程可以继续访问共享资源
                semaphore.release();
                System.out.println("Task " + taskId + " has released the shared resource.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

