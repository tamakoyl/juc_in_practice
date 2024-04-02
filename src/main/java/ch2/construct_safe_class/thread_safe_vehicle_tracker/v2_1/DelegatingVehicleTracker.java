package ch2.construct_safe_class.thread_safe_vehicle_tracker.v2_1;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:Tamako
 * @Date:2024/3/21 15:10
 * @Description:使用Java 监视器模式来达到线程安全的目的
 * 构建大的线程安全模块，应该从构建小的线程安全模块入手，从Point入手
 * 1.直接把 Point 变为一个不可变对象
 * 2.构建一个可变但是线程安全的 Point 类，即给 Point 类中的 get 和 set 方法上加上同步，
 * 然后我们在 MonitorVehicleTracker 中就不用再使用同步了，相当于缩小了同步代码块的大小
 */
public class DelegatingVehicleTracker {
    private final Map<String, ImmutablePoint> locations;
    private final Map<String, ImmutablePoint> unmodifiableMap;


    public DelegatingVehicleTracker(Map<String, ImmutablePoint> pointMap) {
        // 通过使用ConcurrentHashMap来保证locations的读写安全
        locations = new ConcurrentHashMap<>(pointMap);//共享变量
        //这个类是 Map 的线程安全装饰类，具体实现为把传入的 Map m 保存在自己的域中，
        // 然后把所有的能修改该 Map 的方法的实现改成：throw new UnsupportedOperationException();
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }


    public Map<String, ImmutablePoint> getLocations() {
        return unmodifiableMap;
    }

    //创建时的不变视图
    public ImmutablePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) throws IllegalAccessException {
        // 这里直接new一个新的ImmutablePoint对象替代原理的对象
        if (locations.replace(id, new ImmutablePoint(x, y)) == null) {
            throw new IllegalAccessException("No such ID: " + id);
        }
    }


}
