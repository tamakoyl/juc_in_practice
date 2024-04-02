package ch2.construct_safe_class.thread_safe_vehicle_tracker.v1;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Tamako
 * @Date:2024/3/21 15:10
 * @Description:使用Java 监视器模式来达到线程安全的目的
 * 构建大的线程安全模块，应该从构建小的线程安全模块入手，从Point入手
 * 1.直接把 Point 变为一个不可变对象
 * 2.构建一个可变但是线程安全的 Point 类，即给 Point 类中的 get 和 set 方法上加上同步，
 *   然后我们在 MonitorVehicleTracker 中就不用再使用同步了，相当于缩小了同步代码块的大小
 */
public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        // 当locations比较大时，这步是一个耗时操作，会长时间占用锁
        // 会出现车辆位置已变，但返回信息保持不变的错误
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) throws IllegalAccessException {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalAccessException("No such ID: " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    // 当locations.size()比较大时，这个方法将会是一个十分费时的操作
    public static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();
        for (String id : m.keySet()) {
            result.put(id, m.get(id));
        }
        return result;
    }
}
