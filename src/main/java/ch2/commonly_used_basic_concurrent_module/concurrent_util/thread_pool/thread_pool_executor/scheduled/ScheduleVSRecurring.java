package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.scheduled;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author:Tamako
 * @Date:2024/3/30 16:44
 * @Description:定时 vs 周期
 */
public class ScheduleVSRecurring {

    public static void main(String[] args) {
        //定时任务：具体的时间点
        dailyBackupTask();

        //周期任务: 没有具体时间点
        dataCleanupTask();

    }

    /**
     * 如果系统需要每隔一段时间检查数据库中的数据是否过期，并进行清理，那么可以使用周期性任务执行。
     * 系统会在每次任务执行完成后等待一段时间再次执行，
     * 不是在固定的时间点执行
     */
    public static void dataCleanupTask() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new CleanupTask(), 0, 24 * 60 * 60 * 1000); // 每隔一天执行一次
    }

    static class CleanupTask extends TimerTask {
        @Override
        public void run() {
            // 执行数据清理任务的逻辑
            System.out.println("Performing data cleanup...");
        }
    }
    /**
     * 当一个系统需要在每天凌晨执行某些任务时，比如数据备份，那么可以使用定时任务执行。这样，系统会在每天的固定时间点执行数据备份任务，如凌晨3点
     */
    static void dailyBackupTask(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new BackupTask(), getDelayToNextDay(), 24 * 60 * 60 * 1000); // 每隔一天执行一次
    }
    static class BackupTask extends TimerTask {
        @Override
        public void run() {
            // 执行数据备份任务的逻辑
            System.out.println("Performing daily backup...");
        }
    }

    // 计算距离下一个凌晨的延迟时间
    private static long getDelayToNextDay() {
        long currentTime = System.currentTimeMillis();
        long nextDayStart = (currentTime / (24 * 60 * 60 * 1000) + 1) * (24 * 60 * 60 * 1000); // 下一个凌晨的时间戳
        return nextDayStart - currentTime;
    }
}
