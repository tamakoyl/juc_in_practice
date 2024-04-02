package ch2.commonly_used_basic_concurrent_module.concurrent_vec;

/**
 * @Author:Tamako
 * @Date:2024/3/21 16:32
 * @Description:ConcurrentHashMap锁分段实现原理
 */
public class ConcurrentHashMapFundamental {
    /*
    // 同步策略：buckets[n]由locks[n % N_LOCKS]保护
    private static final int N_LOCKS = 16;
    private final Node[] buckets;
    private final Object[] locks; // N_LOCKS个锁
    private static class Node {
        Node next;
        Object key;
        Object value;
    }
    public ConcurrentHashMapFundamental(int numBuckets) {
        buckets = new Node[numBuckets];
        locks = new Object[N_LOCKS];
        for (int i = 0; i < N_LOCKS; i++)
            locks[i] = new Object();
    }
    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
    public Object get(Object key) {
        int hash = hash(key);
        synchronized (locks[hash % N_LOCKS]) { // 分段加锁
            for (Node m = buckets[hash]; m != null; m = m.next)
                if (m.key.equals(key))
                    return m.value;
        }
        return null;
    }
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % N_LOCKS]) { // 分段加锁
                buckets[i] = null;
            }
        }
    }


    */
}
