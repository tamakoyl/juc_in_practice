package ch2.construct_safe_class.thread_safe_vehicle_tracker.v2_2;

/**
 * @Author:Tamako
 * @Date:2024/3/21 15:10
 * @Description:
 */
public class SafePoint {
    public int x, y;
    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public synchronized int[] get(){
        return new int[]{x,y};
    }

   public synchronized void set(int x,int y){
        this.x = x;
        this.y = y;
   }

}
