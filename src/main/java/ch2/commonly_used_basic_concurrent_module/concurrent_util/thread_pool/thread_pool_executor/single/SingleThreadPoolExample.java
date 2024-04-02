package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.single;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:Tamako
 * @Date:2024/3/30 16:20
 * @Description: 单个工作者线程来执行任务，如果这个线程异常结束，会创建另一个线程来替代。能确保依照任务在队列中的顺序来串行执行
 * 所有任务都会按照它们被提交的顺序依次执行，保证了任务的顺序性
 */
public class SingleThreadPoolExample {
    /*
     public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,// 线程池的大小固定为1
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>())); // 使用容量为 Integer.MAX_VALUE 的工作队列
    }
     */
    public static void main(String[] args) {
        // 创建 SingleThreadPool
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 模拟多个任务按顺序执行的场景，提交多个任务给线程池处理
        for (int i = 0; i < 5; i++) {
            int taskNumber = i + 1;
            executor.submit(new Task(taskNumber));
        }

        // 关闭线程池
        executor.shutdown();
    }

    static class Task implements Runnable {
        private int taskNumber;

        public Task(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void run() {
            // 模拟任务的执行
            System.out.println("Task " + taskNumber + " is executing on thread: " + Thread.currentThread().getName());
            // 模拟任务执行时间
            try {
                Thread.sleep(1000); // 假设任务执行时间为 1 秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
