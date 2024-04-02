package ch2.commonly_used_basic_concurrent_module.concurrent_util.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author:Tamako
 * @Date:2024/3/21 16:49
 * @Description:阻塞队列实现原理
 */
public class BlockingQueueFundamental {
    /*public class ArrayBlockingQueue<E> extends AbstractQueue<E>
		implements BlockingQueue<E>, java.io.Serializable {
	int count;  // 队列中元素的个数
	final ReentrantLock lock;  // 下面的两个Condition绑定在这个锁上
	private final Condition notEmpty;  // 用来等待take的条件
	private final Condition notFull;  // 用来等待put的条件

	public ArrayBlockingQueue(int capacity, boolean fair) {
        // 省略...
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }

    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();  //是以可中断的方式获取锁，如果当前线程被中断，则会抛出 InterruptedException
        try {
            while (count == items.length)
                //队列满了，阻塞当前线程，等待队列不满的信号
                notFull.await();  // 轮询count值，等待count < items.length
            enqueue(e);
        } finally {
            lock.unlock();//确保在任何情况下都会释放锁，以免造成死锁等问题
        }
    }

    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                //队列空了，阻塞当前线程，等待队列不空的信号
                notEmpty.await();  // 轮询count值，等待count > 0
            return dequeue();  // 包含notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(E x) {
        // assert lock.getHoldCount() == 1;
        // assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        notEmpty.signal();  // 会唤醒在等待的take操作：notEmpty.await();
    }

    private E dequeue() {
        // assert lock.getHoldCount() == 1;
        // assert items[takeIndex] != null;
        final Object[] items = this.items;
        @SuppressWarnings("unchecked")
        E x = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length)
            takeIndex = 0;
        count--;
        if (itrs != null)
            itrs.elementDequeued();
        notFull.signal();  // 会唤醒在等待的put操作
        return x;
    }
}*/


    public static void main(String[] args) {
        // 创建一个容量为 5 的阻塞队列
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // 创建生产者线程
        Thread producer = new Thread(() -> {
            try {
                // 生产者不断地往队列中放入数据
                for (int i = 1; i <= 10; i++) {
                    queue.put(i); // 将数据放入队列，如果队列已满则阻塞
                    System.out.println("Produced: " + i);
                    Thread.sleep(1000); // 生产者休眠1秒钟
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 创建消费者线程
        Thread consumer = new Thread(() -> {
            try {
                // 消费者不断地从队列中取出数据
                for (int i = 0; i < 10; i++) {
                    int value = queue.take(); // 从队列中取出数据，如果队列为空则阻塞
                    System.out.println("Consumed: " + value);
                    Thread.sleep(5000); // 消费者休眠2秒钟
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 启动生产者和消费者线程
        producer.start();
        consumer.start();
    }
}
