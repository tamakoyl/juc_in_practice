package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.future;

import java.util.concurrent.*;

/**
 * @Author:Tamako
 * @Date:2024/4/1 17:17
 * @Description:并行数据加载
 */
public class ParallelDataLoadingExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<String> loadFromDatabase = () -> {
            // 模拟数据库加载
            Thread.sleep(1000);
            return "数据库数据";
        };

        Callable<String> loadFromFileSystem = () -> {
            // 模拟文件系统加载
            Thread.sleep(800);
            return "文件系统数据";
        };

        Callable<String> loadFromRemoteService = () -> {
            // 模拟远程服务加载
            Thread.sleep(1200);
            return "远程服务数据";
        };

        Future<String> databaseFuture = executor.submit(loadFromDatabase);
        Future<String> fileSystemFuture = executor.submit(loadFromFileSystem);
        Future<String> remoteServiceFuture = executor.submit(loadFromRemoteService);

        try {
            System.out.println(databaseFuture.get());
            System.out.println(fileSystemFuture.get());
            System.out.println(remoteServiceFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}

