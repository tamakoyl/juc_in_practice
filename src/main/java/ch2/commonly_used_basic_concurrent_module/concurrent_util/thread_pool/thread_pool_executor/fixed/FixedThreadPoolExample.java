package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.fixed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Tamako
 * @Date:2024/3/29 15:51
 * @Description:固定长度的线程池
 * 构造器
 * public static ExecutorService newFixedThreadPool(int nThreads) {
 *     return new ThreadPoolExecutor(nThreads, nThreads,  // 线程池大小不可扩展
 *                                   0L, TimeUnit.MILLISECONDS,  // 多余线程会被立即终止
 *                                   new LinkedBlockingQueue<Runnable>());
 *     							// 使用容量为 Integer.MAX_VALUE 的工作队列
 *     							// 由于使用了无界队列，不会拒绝任务，所以不会调用 handler
 * }
 *
 * feature: 固定长度的线程池，每当提交一个任务时就创建一个线程，直到达到线程池的最大数量，如果某个线程由于发生了未预期的 Exception 而结束，那么线程池会补充一个新的线程。
 *
 * 适用场景: 固定大小线程池适用于任务量已知且稳定，且需要控制线程数量和资源消耗的场景
 */
public class FixedThreadPoolExample {
    public static void main(String[] args) {
        // 创建固定大小为 5 的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 提交 10 个任务给线程池处理
        for (int i = 0; i < 10; i++) {
            // 创建一个新任务并提交给线程池执行
            Task task = new Task(i);
            executor.submit(task);
        }

        // 关闭线程池
        executor.shutdown();
    }

    // 任务类，实现了 Runnable 接口
    static class Task implements Runnable {
        private int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            // 打印任务信息和线程信息
            System.out.println("Task ID : " + taskId + " performed by " + Thread.currentThread().getName());
            try {
                // 模拟任务执行时间
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
