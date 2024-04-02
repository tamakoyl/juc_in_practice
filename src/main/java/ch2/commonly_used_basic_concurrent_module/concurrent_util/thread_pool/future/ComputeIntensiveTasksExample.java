package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.future;

import java.util.concurrent.*;

/**
 * @Author:Tamako
 * @Date:2024/4/1 17:18
 * @Description:计算密集型任务的结果合并
 */
public class ComputeIntensiveTasksExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> task1 = () -> {
            Thread.sleep(500); // 模拟计算
            return 10;
        };

        Callable<Integer> task2 = () -> {
            Thread.sleep(800); // 模拟计算
            return 20;
        };

        Future<Integer> future1 = executor.submit(task1);
        Future<Integer> future2 = executor.submit(task2);

        try {
            Integer result1 = future1.get();
            Integer result2 = future2.get();
            System.out.println("总和: " + (result1 + result2));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
