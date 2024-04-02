package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control.semaphore;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * @Author:Tamako
 * @Date:2024/3/25 19:04
 * @Description:生产者-消费者模式：
 * 在生产者-消费者模式中，Semaphore 可以用于控制生产者和消费者之间的数据传输速度。
 * 可以使用 Semaphore 来限制生产者和消费者的并发数量，以平衡生产和消费的速度。
 */
public class ProducerConsumerExample {
    private static final int BUFFER_SIZE = 10;
    private final Semaphore emptySemaphore = new Semaphore(BUFFER_SIZE); // 空闲空间信号量
    private final Semaphore fullSemaphore = new Semaphore(0); // 满空间信号量
    private final Semaphore mutex = new Semaphore(1); // 互斥信号量

    private final Queue<Integer> buffer = new LinkedList<>();

    public void produce(int item) throws InterruptedException {
        emptySemaphore.acquire(); // 获取空闲空间许可证 10-1
        mutex.acquire(); // 获取互斥许可证

        buffer.add(item);
        System.out.println("Produced: " + item);

        mutex.release(); // 释放互斥许可证
        fullSemaphore.release(); // 增加满空间许可证0+1
    }

    public int consume() throws InterruptedException {
        fullSemaphore.acquire(); // 获取满空间许可证
        mutex.acquire(); // 获取互斥许可证

        if (buffer.isEmpty()) {
            // 缓冲区为空，抛出异常或返回特定值
            throw new IllegalStateException("Buffer is empty");
        }

        int item = buffer.poll();
        System.out.println("Consumed: " + item);

        mutex.release(); // 释放互斥许可证
        emptySemaphore.release(); // 增加空闲空间许可证

        return item;
    }

    public static void main(String[] args) {
        ProducerConsumerExample example = new ProducerConsumerExample();

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    example.produce(i);
                    Thread.sleep(1000); // 生产一个物品后休眠一秒钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    example.consume();
                    Thread.sleep(2000); // 消费一个物品后休眠两秒钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
