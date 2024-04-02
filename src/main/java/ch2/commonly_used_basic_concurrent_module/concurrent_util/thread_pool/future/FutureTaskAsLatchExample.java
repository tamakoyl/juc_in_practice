package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.future;

import java.util.concurrent.*;

/**
 * @Author:Tamako
 * @Date:2024/4/1 17:21
 * @Description:使用FutureTask作为便捷的闭锁:
 * 闭锁提供了一种强大的机制来同步线程的执行和确保任务按照预期的顺序执行。
 * 类似于CountDownLatch 和 CyclicBarrier
 */
public class FutureTaskAsLatchExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 定义两个Callable任务
        Callable<String> task1 = () -> {
            Thread.sleep(1000); // 模拟任务1的执行时间
            return "任务1完成";
        };

        Callable<String> task2 = () -> {
            Thread.sleep(1500); // 模拟任务2的执行时间
            return "任务2完成";
        };

        // 使用FutureTask封装Callable任务
        FutureTask<String> futureTask1 = new FutureTask<>(task1);
        FutureTask<String> futureTask2 = new FutureTask<>(task2);

        // 提交任务到线程池执行
        executor.execute(futureTask1);
        executor.execute(futureTask2);

        // 等待两个任务都完成
        try {
            System.out.println(futureTask1.get()); // 等待任务1完成
            System.out.println(futureTask2.get()); // 等待任务2完成
            System.out.println("所有任务执行完成，主线程可以继续执行");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }


    public static void countDownLatchExample(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("任务1完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("任务2完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start();

        // 等待所有任务完成
        latch.await();
        System.out.println("所有任务执行完成，主线程可以继续执行");
    }
}
