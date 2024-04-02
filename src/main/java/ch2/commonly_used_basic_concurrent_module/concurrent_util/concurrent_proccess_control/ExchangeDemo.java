package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Tamako
 * @Date:2024/3/26 09:27
 * @Description:线程交换信息
 * 一个用于两个线程间交换数据的工具类。如果第一个线程先执行了exchange(V)方法，它会阻塞在那里，等待第二个线程执行exchange(V)方法，exchange(V)会返回另一个线程传入的数据
 */
public class ExchangeDemo {
    private static final Exchanger<String> exchange = new Exchanger<>();

    private static ExecutorService pool = Executors.newFixedThreadPool(2);

    // 用来保证线程池在两个线程执行完之后再关闭
    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) {
        pool.execute(() -> {
            try {
                String data = "第一个线程的结果";
                Thread.sleep(100);
                String res = exchange.exchange(data);
                System.out.println("我是第一个线程，我收到另一个线程的结果为：" + res);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(() -> {
            try {
                String data = "第二个线程的结果";
                Thread.sleep(1000);
                String res = exchange.exchange(data);
                System.out.println("我是第二个线程，我收到另一个线程的结果为：" + res);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            latch.await();  // 等待两线程执行完，然后关闭线程池
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
