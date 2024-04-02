package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.cache_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Tamako
 * @Date:2024/3/30 15:53
 * @Description:文件上传服务器监听文件上传请求，并将上传任务提交给 CachedThreadPool 处理。
 * 由于上传请求的数量和文件大小不确定，CachedThreadPool 能够动态地调整线程池的大小，以处理不同大小的文件上传请求
 */
public class FileUploadServer {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        // 监听文件上传请求，并提交任务给 CachedThreadPool 处理
        for (int i = 0; i < 100; i++) { // 假设有 10 个上传请求
            String fileName = "file" + i + ".txt";
            executor.submit(new FileUploadTask(fileName));
        }
    }

    static class FileUploadTask implements Runnable {
        private String fileName;

        public FileUploadTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            // 文件上传的逻辑
            System.out.println("Uploading file: " + fileName + " on thread: " + Thread.currentThread().getName());
            // 模拟文件上传时间
            try {
                Thread.sleep(1000);//线程处理时间长，会不断创建新的线程来处理，创建100个
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
