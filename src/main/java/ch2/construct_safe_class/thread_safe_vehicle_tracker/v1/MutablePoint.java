package ch2.construct_safe_class.thread_safe_vehicle_tracker.v1;

/**
 * @Author:Tamako
 * @Date:2024/3/21 15:10
 * @Description:线程不安全的 Point 类，用来表示车辆的坐标。
 */
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this(p.x, p.y);
    }

    public MutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
