package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author:Tamako
 * @Date:2024/3/25 17:15
 * @Description:当 CyclicBarrier 的计数器被重置时，意味着可以重新使用该 CyclicBarrier 实例来进行新一轮的线程同步。
 * 这种重置行为允许在多个阶段性任务中重复使用 CyclicBarrier
 * <p>
 * 假设有一个赛跑比赛，比赛分为三个阶段，每个阶段都有一条跑道，每条跑道上有多个运动员参与比赛。
 * 在每个阶段结束时，需要等待所有跑道上的运动员都到达终点，然后才能进行下一个阶段的比赛。
 * 我们可以使用 CyclicBarrier 来实现这个功能，并且在每个阶段结束时重置 CyclicBarrier，以便进行下一轮比赛
 */
public class Race {
    private static final int NUM_THREADS = 5;
    private static final int NUM_PHASES = 3;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, new Runnable() {
            @Override
            public void run() {
                System.out.println("All runners have completed this phase race!");
            }
        });

        for (int i = 1; i <= NUM_PHASES; i++) {
            System.out.println("Starting phase " + i);

            for (int j = 1; j <= NUM_THREADS; j++) {
                new Thread(new Runner(barrier, "Runner " + j, i)).start();
            }

            // 等待所有运动员完成比赛
            System.out.println("-----------------------------------");
            try {
                Thread.sleep(1000); //主线程休眠 模拟比赛时间
                System.out.println("-----------------------------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All race has finished!!");
    }

    static class Runner implements Runnable {
        private final CyclicBarrier barrier;
        private final String name;
        private final int phase;

        Runner(CyclicBarrier barrier, String name, int phase) {
            this.barrier = barrier;
            this.name = name;
            this.phase = phase;
        }

        @Override
        public void run() {
            System.out.println(name + " starts phase " + phase);
            try {
                // 模拟比赛时间
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(name + " finishes phase " + phase);
                barrier.await(); // 等待其他运动员完成比赛
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
