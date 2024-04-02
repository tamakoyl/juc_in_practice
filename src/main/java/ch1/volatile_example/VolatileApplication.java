package ch1.volatile_example;

/**
 * @Author:Tamako
 * @Date:2024/3/21 13:37
 * @Description:volatile 的应用场景
 */
public class VolatileApplication  extends Thread{
    private volatile boolean stopRequested = false;
    public void run() {
        while (!stopRequested) {
            // 执行某种任务
            System.out.println("Background thread is running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 处理中断异常
            }
        }
        System.out.println("Background thread stopped.");
    }

    public void stopThread() {
        stopRequested = true; // 设置终止标志
    }
}
