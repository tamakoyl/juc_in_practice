package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.cache_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Tamako
 * @Date:2024/3/29 16:00
 * @Description:
 * 适用场景:
 *1. 任务数量不确定：适用于任务数量不确定的情况，因为CachedThreadPool会根据任务的数量动态地调整线程池的大小，从而避免了固定大小线程池可能出现的资源浪费或任务堆积。
 *2. 短时任务：适用于执行时间较短的任务，因为CachedThreadPool会尽量重用之前创建的线程，避免频繁地创建和销毁线程带来的开销。
 *3. 高并发场景：适用于需要处理高并发任务的场景，因为CachedThreadPool可以根据任务的数量动态调整线程池的大小，从而更有效地处理大量的并发任务。
 *4. IO密集型任务：适用于执行IO密集型任务的场景，例如网络请求、文件读写等，因为CachedThreadPool可以根据任务的阻塞情况动态调整线程池的大小，从而更有效地利用系统资源。
 *5. 任务执行时间不确定：适用于任务执行时间不确定的场景，因为CachedThreadPool会根据需要动态地创建新线程，从而确保及时地处理任务。
 *
 * 注意：
 * 1.池中不会有空闲线程，也不会有等待的线程
 * 2.一旦任务到达的速度大于线程池处理任务的速度，就会创建一个新的线程给任务
 * 3.与另外两个线程池不同的地方在于，这个工作队列并不是用来放还没有执行的任务的，而是用来放执行过任务后`空闲`下的线程的，空闲下来的线程会被：SynchronousQueue#poll(keepAliveTime, TimeUnit.NANOSECONDS) poll 到工作队列中等待 60s，如果这 60s 有新的任务到达了，这个线程就被派出去执行任务，如果没有，就销毁。
 */
public class CachedThreadPoolExample {
    public static void main(String[] args) {
        // 创建CachedThreadPool
        /*
         public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,//// 初始为0，线程池中的线程数是无界的
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }
         */
        ExecutorService executor = Executors.newCachedThreadPool();

        // 提交任务
        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Executing task " + taskNumber + " on thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task " + taskNumber + " completed");
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
}
