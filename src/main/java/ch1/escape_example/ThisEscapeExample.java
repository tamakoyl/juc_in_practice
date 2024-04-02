package ch1.escape_example;

/**
 * @Author:Tamako
 * @Date:2024/3/21 13:57
 * @Description:this引用逸出：
 * 指的是在对象构造过程中，当一个对象还没有构造完成，但它的引用就已经逃离了构造函数，被其他线程所使用
 */
public class ThisEscapeExample {
    private int value;

    public ThisEscapeExample() {
        new Thread(() -> System.out.println("Value is: " + this.value)).start();
        try {
            Thread.sleep(1000); // 模拟其他初始化操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.value = 42; // 在对象构造未完成时，就将this逸出，被其他线程引用
    }
}
