package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Tamako
 * @Date:2024/3/30 16:31
 * @Description:
 *
 * 定时任务执行：适用于需要定时执行某些任务的场景，比如定时数据备份、定时数据清理等
 *
 * 创建:
 *  Executors.newScheduledThreadPool(1);
 * public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
 *     return new ScheduledThreadPoolExecutor(corePoolSize);
 * }
 *
 * 提交任务：
 * public <V> ScheduledFuture<V> schedule(Callable<V> callable,
 *                                        long delay,
 *                                        TimeUnit unit)
 *
 * 实现原理:
 * 1.使用 DelayWorkQueue 作为工作队列
 *  public ScheduledThreadPoolExecutor(int corePoolSize) {
 *         super(corePoolSize, Integer.MAX_VALUE,
 *               DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
 *               new DelayedWorkQueue());//工作队列
 *     }
 *
 *
 * 2.ScheduledThreadPoolExecutor 会把待执行的任务 ScheduledFutureTask 放到工作队列中
 *  public ScheduledFuture<?> schedule(Runnable command,
 *                                        long delay,
 *                                        TimeUnit unit) {
 *         if (command == null || unit == null)
 *             throw new NullPointerException();
 *         RunnableScheduledFuture<Void> t = decorateTask(command,
 *             new ScheduledFutureTask<Void>(command, null,
 *                                           triggerTime(delay, unit),
 *                                           sequencer.getAndIncrement()));
 *         delayedExecute(t);
 *         return t;
 *     }
 *
 * 3. ScheduledFutureTask 中有以下 3 个主要的成员变量
 * (1) long time：表示该任务将要被执行的具体时间；
 * (2) long sequenceNumber：表示任务被添加到 ScheduledThreadPoolExecutor 中的序号；
 * (3) long period：表示任务执行的间隔周期。
 * ScheduledFutureTask(Runnable r, V result, long triggerTime,
 *                             long period, long sequenceNumber) {
 *             super(r, result);
 *             this.time = triggerTime;
 *             this.period = period;
 *             this.sequenceNumber = sequenceNumber;
 *         }
 *
 * 4. 任务执行
 * 线程从 DelayWorkQueue 中获取到期的任务；
 * 执行这个任务；
 * 修改这个任务的 time 为下一次的执行时间；
 * 将该任务再次 add 进 DelayWorkQueue。
 *
 * VS Timer
 * (1) Timer 在执行所有定时任务时只会创建一个线程。如果有一个任务执行时间太长导致它后面的任务超时，那么后面超时的任务会立即执行，
 *     从而破坏了其他 TimerTask 的准时执行。线程池能弥补这个缺陷，因为它可以提供多个线程来执行延时任务和周期任务。
 * (2) 线程泄漏：Timer 线程并不捕获未检查异常，当 TimerTask 抛出未检查的异常时将终止定时线程。
 *     这种情况下，整个 Timer都会被取消，将导致已经被调度但尚未执行的 TimerTask 将不会再执行，新的任务也不能被调度。
 *
 */
public class ScheduledTaskExample {
    public static void main(String[] args) {
        // 创建 ScheduledThreadPoolExecutor
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // 定时执行任务：每隔一定时间执行一次任务
        executor.scheduleAtFixedRate(new Task(), 0, 5, TimeUnit.SECONDS);
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            // 模拟定时执行的任务
            System.out.println("Task is executing at: " + System.currentTimeMillis());
        }
    }
}
