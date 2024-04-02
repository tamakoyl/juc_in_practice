package ch2.construct_safe_class.how_add_func_in_thread_safe_class.decorate_class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author:Tamako
 * @Date:2024/3/25 16:47
 * @Description:装饰类的方式来实现
 */
public class ListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean contains = list.contains(x);
            if (!contains) {
                list.add(x);
            }
            return !contains;
        }
    }
}
