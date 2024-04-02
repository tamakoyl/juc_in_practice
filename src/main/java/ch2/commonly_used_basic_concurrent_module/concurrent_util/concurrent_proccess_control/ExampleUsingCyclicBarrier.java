package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control;

/**
 * @Author:Tamako
 * @Date:2024/3/25 17:07
 * @Description:主要使用场景是当多个线程需要在某个点上进行同步，等待所有线程都到达该点之后再同时继续执行
 *
 * 应用场景：
 * 1.多线程计算任务的拆分与合并：可以将一个大型计算任务拆分成多个子任务，每个子任务由一个线程执行，然后使用 CyclicBarrier 等待所有子任务执行完毕后再将结果合并。这种方式可以有效地利用多核 CPU 的计算能力，提高计算效率。
 * 2.并行数据处理：例如，可以将一个大型数据集拆分成多个小数据块，每个数据块由一个线程处理，然后使用 CyclicBarrier 等待所有线程处理完毕后再将处理结果合并。这种方式可以加快数据处理的速度，提高系统的吞吐量。
 * 3.模拟多个参与者的协同工作：例如，可以模拟多个运动员在比赛中的情况，每个运动员由一个线程表示，比赛开始时所有运动员同时开始跑步，使用 CyclicBarrier 等待所有运动员都到达终点后公布比赛结果。
 * 4.多个线程协作解决复杂问题：例如，可以将一个复杂的问题拆分成多个子问题，每个子问题由一个线程解决，然后使用 CyclicBarrier 等待所有线程解决完毕后再将解决方案合并。这种方式可以加速问题的解决过程，提高系统的处理能力。
 */
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ExampleUsingCyclicBarrier {
    public static void main(String[] args) {
        // 创建一个CyclicBarrier对象，参数表示需要等待的线程数量
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads have reached the barrier. Continue execution.");
            }
        });

        // 创建并启动三个线程
        Thread thread1 = new Thread(new Worker(barrier, "Worker 1"));
        Thread thread2 = new Thread(new Worker(barrier, "Worker 2"));
        Thread thread3 = new Thread(new Worker(barrier, "Worker 3"));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    // Worker类，模拟需要同时到达某个点的任务
    static class Worker implements Runnable {
        private final CyclicBarrier barrier;
        private final String name;

        Worker(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            // 模拟任务执行
            System.out.println(name + " is executing its task...");
            try {
                Thread.sleep(2000); // 模拟任务执行时间
                System.out.println(name + " has reached the barrier.");
                barrier.await(); // 屏障点：等待其他线程到达
                System.out.println(name + " continues execution after reaching the barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

