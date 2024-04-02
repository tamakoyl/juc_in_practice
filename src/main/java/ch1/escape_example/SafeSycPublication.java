package ch1.escape_example;

/**
 * 同步机制来确保属性初始化的原子性和可见性
 */
public class SafeSycPublication {
    private int value;

    public SafeSycPublication() {
        synchronized (this) {
            this.value = 42;
        }
        new Thread(() -> {
            synchronized (this) {
                System.out.println("Value is: " + this.value);
            }
        }).start();
    }

    public static void main(String[] args) {
        SafeSycPublication obj = new SafeSycPublication();
    }
}
