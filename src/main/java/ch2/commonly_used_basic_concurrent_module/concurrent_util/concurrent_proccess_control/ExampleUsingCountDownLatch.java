package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:Tamako
 * @Date:2024/3/25 16:58
 * @Description:CountDownLatch
 *  可以让一个或多个线程等待其他线程操作完成在继续执行，不可以循环使用，只能使用一次[这个可以用在初始化系统信息的时候]
 *
 *  应用场景：
 * 1.启动多个线程之后等待它们全部完成再执行主线程：主线程可以通过 CountDownLatch 来等待其他所有线程执行完毕后再执行特定的操作，例如收集其他线程的执行结果。
 * 2.多个线程等待某个事件的发生：例如，一组服务启动线程可以等待所有服务都成功启动后再继续执行后续操作。
 * 3.控制并发线程的执行顺序：通过 CountDownLatch 可以控制多个线程的执行顺序，例如，可以让线程 A 在线程 B 和线程 C 都执行完毕后再执行。
 * 4.在测试中等待多个线程执行完毕：在进行并发测试时，可以使用 CountDownLatch 来等待所有测试线程执行完毕后再进行结果统计
 */
public class ExampleUsingCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个CountDownLatch对象，参数表示需要等待的线程数量
        CountDownLatch latch = new CountDownLatch(3);

        // 创建并启动三个线程
        Thread thread1 = new Thread(new Worker(latch, "Worker 1"));
        Thread thread2 = new Thread(new Worker(latch, "Worker 2"));
        Thread thread3 = new Thread(new Worker(latch, "Worker 3"));

        thread1.start();
        thread2.start();
        thread3.start();

        // 等待所有线程执行完毕
        latch.await();

        // 所有线程执行完毕后，主线程继续执行
        System.out.println("All workers have completed their tasks. Main thread continues.");
    }

    // Worker类，模拟需要执行的任务
    static class Worker implements Runnable {
        private final CountDownLatch latch;
        private final String name;

        Worker(CountDownLatch latch, String name) {
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {
            // 模拟工作任务
            System.out.println(name + " is working...");
            try {
                Thread.sleep(2000); // 模拟工作任务执行时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " has completed its task.");

            // 工作完成后，调用countDown()方法减少计数器
            latch.countDown();
        }
    }
}
