package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.cache_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Tamako
 * @Date:2024/3/30 15:48
 * @Description:Web 服务器启动时创建了 CachedThreadPool，并预先创建了一定数量的线程，以便立即处理到达的请求。由于请求的数量可能不确定，CachedThreadPool 能够根据需要动态地调整线程池的大小
 */
public class WebServer {
    private static final int MAX_THREADS = 100; // 最大线程数
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        // 在 Web 服务器启动时，创建 CachedThreadPool
        // 在请求到达时，提交任务给线程池处理
        for (int i = 0; i < MAX_THREADS; i++) {
            executor.submit(new RequestHandler());
        }
    }

    static class RequestHandler implements Runnable {
        @Override
        public void run() {
            // 处理请求的逻辑
            System.out.println("Handling request on thread: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(0);//处理时间短，可以复用创建好的线程
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
