package ch2.construct_safe_class.thread_safe_vehicle_tracker.v2_1;

/**
 * @Author:Tamako
 * @Date:2024/3/21 15:10
 * @Description:不可修改的Point类
 */
public class ImmutablePoint {
    public final int x, y;
    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
