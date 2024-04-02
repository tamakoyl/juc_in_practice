package ch2.construct_safe_class.thread_safe_vehicle_tracker.v2_2;

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
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;


    public PublishingVehicleTracker(Map<String, SafePoint> pointMap) {
        // 通过使用ConcurrentHashMap来保证locations的读写安全
        locations = new ConcurrentHashMap<>(pointMap);//共享变量
        //这个类是 Map 的线程安全装饰类，具体实现为把传入的 Map m 保存在自己的域中，
        // 然后把所有的能修改该 Map 的方法的实现改成：throw new UnsupportedOperationException();
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }


    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    //创建时的不变视图
    public SafePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) throws IllegalAccessException {
        // 因为Point已经改成线程安全的了，我们可以通过Point自己的set和get方法放心大胆的修改它
        SafePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalAccessException("No such ID: " + id);
        }
        loc.set(x, y);
    }


}
