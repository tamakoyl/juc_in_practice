package ch2.commonly_used_basic_concurrent_module.concurrent_util.thread_pool.thread_pool_executor.rejected_handler;

import java.util.*;

/**
 * @Author:Tamako
 * @Date:2024/3/30 18:40
 * @Description: 处理任务被拒绝执行的策略接口，当线程池无法接受新任务时，会调用 RejectedExecutionHandler 来处理被拒绝的任务
 * 4种policy来实现RejectedExecutionHandler
 * (1)AbortPolicy （默认）（Abort：流产）
 * (2)DiscardPolicy （Discard：抛弃）
 * (3)DiscardOldestPolicy
 * (4) CallerRunsPolicy （既不抛出异常，也不抛弃任务）
 */
public class Description {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 字符串长度
        String s = scanner.next(); // 输入字符串

        int result = countBalancedSubsequences(n, s);
        System.out.println(result);
    }

    public static int countBalancedSubsequences(int n, String s) {
        Map<Character, Integer> countMap = new HashMap<>();

        // 统计每个字符的出现次数
        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        List<Integer> values = new ArrayList<>(countMap.values());
        int cnt = 0;
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                int m = Math.min(values.get(i), values.get(j));
                int k = Math.max(values.get(i), values.get(j));
                cnt += computeSumOfProducts(k, m);
            }
        }

        return cnt % (10 ^ 9 + 7);
    }

    // 计算从1到m的组合数乘以n的组合数，然后从1到m求和
    public static int computeSumOfProducts(int n, int m) {
        int sum = 0;
        for (int i = 1; i <= m; i++) {
            int combM = computeCombination(m, i);
            int combN = computeCombination(n, i);
            int product = combM * combN;
            sum += product;
        }
        return sum;
    }

    // 计算组合数
    public static int computeCombination(int n, int k) {
        int numerator = computeFactorial(n);
        int denominator = computeFactorial(k) * computeFactorial(n - k);
        return numerator / denominator;
    }

    // 计算阶乘
    public static int computeFactorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

}
