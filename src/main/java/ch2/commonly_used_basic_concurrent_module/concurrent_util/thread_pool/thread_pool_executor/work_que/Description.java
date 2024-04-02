package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.work_que;

/**
 * @Author:Tamako
 * @Date:2024/3/30 18:31
 * @Description:
 * BlockingQueue<Runnable> workQueue 的设置
 * 1.无界队列
 *     (1)使用无界队列的线程池:newFixedThreadPool、newSingleThreadExecutor
 *     (2)BlockingQueue 选择：无界的 LinkedBlockingQueue
 * 2.有界队列 （可以避免资源耗尽，队列满了的处理方法:RejectedExecutionHandler handler
 *    (1)只有当任务相互独立时，为线程池或工作队列设置界限才是合理的。如果任务之间存在依赖性，那么有界的线程池或队列就可能导致线程“饥饿”死锁问题
 *         ex：任务间有依赖性——消费者任务必须等待生产者任务将数据放入队列后才能执行
 *         现在假设使用一个有界的队列作为生产者任务和消费者任务之间的通信工具，并且队列已经达到了最大容量。
 *         这种情况下，如果生产者任务尝试将数据放入已满的队列中，它将被阻塞，直到有消费者任务将数据从队列中取出，为队列腾出空间。
 *         但是，如果消费者任务在队列已满的情况下无法运行（例如，由于线程池的最大线程数已达到），那么它将无法处理队列中的数据，从而导致生产者任务一直被阻塞，无法继续执行。
 *         这种情况下，生产者任务和消费者任务都可能由于对方的阻塞而无法继续执行，从而导致线程"饥饿"死锁问题
 *    (2)BlockingQueue 选择: ArrayBlockingQueue、有界的 LinkedBlockingQueue、PriorityBlockingQueue
 * 3.同步移交 （SynchronousQueue）
 *  (1)newCachedThreadPool 使用
 *  (2)对于非常大的或者无界的线程池，可以通过使用 SynchronousQueue 来`避免任务排队`
 *  (3)SynchronousQueue 不是一个真正的队列，而是一种在线程之间进行移交的机制
 *  (4)要将一个元素放入 SynchronousQueue 中，必须有另一个线程正在等待接受这个元素。如果没有线程正在等待，并且线程池的当前大小小于最大值，那么 ThreadPoolExecutor 将创建一个新的线程，否则这个任务将被拒绝。
 */
public class Description {
}
