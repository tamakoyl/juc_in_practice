package ch1.escape_example;

/**
 * 将属性声明为 final，这样在构造函数中初始化后就不能再修改，可以避免this引用逸出问题
 */
public class SafeFinalInitialization {
    private final int value;

    public SafeFinalInitialization() {
        this.value = 42;
        new Thread(() -> System.out.println("Value is: " + this.value)).start();
    }

    public static void main(String[] args) {
        SafeFinalInitialization obj = new SafeFinalInitialization();
    }
}
