package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Author:Tamako
 * @Date:2024/3/25 18:59
 * @Description:
 * 在Web 服务器中，经常需要对 HTTP 请求进行限流，以避免服务器负载过高。
 * Semaphore 可以用来限制同时处理的 HTTP 请求数量。
 */
public class HttpRequestHandler {
    private static final int MAX_CONCURRENT_REQUESTS = 100;
    private final Semaphore semaphore = new Semaphore(MAX_CONCURRENT_REQUESTS);

    public void handleRequest(String request) {
        try {
            semaphore.acquire(); // 获取许可证
            // 处理请求
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // 释放许可证
        }
    }
}
