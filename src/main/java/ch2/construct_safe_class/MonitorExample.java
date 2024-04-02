package ch2.construct_safe_class;

/**
 * @Author:Tamako
 * @Date:2024/3/21 15:08
 * @Description:Java 监视器模式
 * 就是把类中所有能访问对象可变状态的方法都加上 synchronized 修饰（简单粗暴）
 */
public class MonitorExample {
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }
}
