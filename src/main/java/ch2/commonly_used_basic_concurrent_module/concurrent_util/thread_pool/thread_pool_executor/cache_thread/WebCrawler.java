package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.cache_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Tamako
 * @Date:2024/3/30 15:50
 * @Description:网络爬虫程序从指定的种子 URL 开始爬取网页，然后解析页面内容并提取链接。
 * 由于爬取的网页数量和页面解析时间不确定，CachedThreadPool 能够根据需要动态地调整线程池的大小，并且重用之前创建的线程，以提高爬取效率
 */
public class WebCrawler {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        // 从种子 URL 开始爬取网页
        String seedUrl = "http://example.com";
        crawl(seedUrl);
    }

    private static void crawl(String url) {
        // 从指定 URL 获取页面内容并解析链接
        // 提交任务给 CachedThreadPool 处理
        executor.submit(() -> {
            // 获取页面内容并解析链接的逻辑
            System.out.println("Crawling URL: " + url + " on thread: " + Thread.currentThread().getName());
            // 模拟页面解析时间
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
