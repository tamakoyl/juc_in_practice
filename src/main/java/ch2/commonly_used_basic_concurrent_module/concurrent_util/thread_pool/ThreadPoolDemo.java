package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Tamako
 * @Date:2024/3/29 15:49
 * @Description:线程池demo
 * Why？
 * 如果不使用线程池，为每一个任务都创建一个线程来执行，我们将会遇到如下问题：
 * 线程的创建和销毁都需要时间，会延迟请求的处理，并且消耗系统资源；
 * 尤其是内存，还会增加 GC 的压力；同时在系统崩溃的临界点，如果多创建一个线程，就会导致系统崩溃，而不是性能的缓慢下降。
 * 如果线程数超过了 CPU 数，增加线程反而会降低性能，因为会出现频繁的上下文切换。
 *
 * advantages:
 * 降低资源消耗：可以重复使用已经创建好的线程
 * 提高响应速度：任务到达时，可以不需要等待线程创建的时间
 * 提高线程的可管理性
 *
 * remark:
 * 在创建 ThreadPoolExecutor 初期，线程并不会立即启动，而是等到有任务提交时才会启动，除非调用 preStartAllCoreThreads
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(15);  // 用来判断线程池是否可以关闭

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("线程开始");
                    Thread.sleep(1000);
                    System.out.println("线程结束");
                    latch.countDown();
                } catch (InterruptedException e) {
                }
            }
        };

        for (int i = 0; i < 15; i++) {
            pool.execute(runnable);
        }
        latch.await();  // 等待线程池中的线程运行完毕
        System.out.println("finish");
        pool.shutdown();
    }
}
