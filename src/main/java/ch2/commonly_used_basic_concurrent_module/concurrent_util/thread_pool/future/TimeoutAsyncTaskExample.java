package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.future;

import java.util.concurrent.*;

/**
 * @Author:Tamako
 * @Date:2024/4/1 17:20
 * @Description:带超时的异步执行
 */
public class TimeoutAsyncTaskExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<String> task = () -> {
            Thread.sleep(2000); // 模拟长时间运行的任务
            return "任务完成";
        };

        Future<String> future = executor.submit(task);

        try {
            System.out.println(future.get(1, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            System.out.println("任务执行超时");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
