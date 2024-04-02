import ch1.volatile_example.VolatileApplication;

/**
 * @Author:Tamako
 * @Date:2024/3/21 13:38
 * @Description:主启动类
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        VolatileApplication volatileApplication = new VolatileApplication();
        volatileApplication.start();
        Thread.sleep(1000);//主线程休眠一会儿
        volatileApplication.stopThread();
    }
}
