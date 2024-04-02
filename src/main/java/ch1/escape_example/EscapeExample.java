package ch1.escape_example;

/**
 * @Author:Tamako
 * @Date:2024/3/21 13:43
 * @Description:逸出例子
 */
public class EscapeExample {
    private String[] states = new String[]{"AC", "AB"};

    // 破坏了封装特性，别的地方拿到数组的引用可以修改数组
    public String[] getStates() {
        return states;
    }

    // 安全的方法
    public String[] safeGetStates() {
        return states.clone();
    }

}
