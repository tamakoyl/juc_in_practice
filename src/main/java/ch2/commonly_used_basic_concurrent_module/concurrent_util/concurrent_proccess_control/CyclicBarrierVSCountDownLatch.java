package ch2.commonly_used_basic_concurrent_module.concurrent_util.concurrent_proccess_control;

/**
 * @Author:Tamako
 * @Date:2024/3/25 18:22
 * @Description:
 * 1.使用次数：
 * CyclicBarrier 的计数器可以被循环使用，一旦所有线程都到达了屏障点，计数器会重置，可以被重新使用。
 * CountDownLatch 的计数器只能使用一次，一旦计数器减为0，就不能再次恢复。如果需要重复使用，需要重新创建一个新的 CountDownLatch 对象。
 *
 * 2.等待线程的行为：
 * CyclicBarrier 等待所有线程都到达屏障点后，所有线程会被释放并且可以继续执行。
 * CountDownLatch 等待所有线程执行完毕后，等待的线程会被释放并且可以继续执行。
 *
 * 3.构造方式：
 * CyclicBarrier 的构造方法中需要指定等待的线程数量，并且可以指定一个可选的 Runnable 对象，在所有线程都到达屏障点时被执行。
 * CountDownLatch 的构造方法中需要指定计数器的初始值，表示需要等待的线程数量，不提供额外的回调方法。
 *
 * 3.用途：
 * CyclicBarrier 通常用于一组线程需要等待彼此达到一个公共的屏障点后再一起继续执行的场景，可以用于分阶段并发任务的同步。
 * CountDownLatch 通常用于一个线程等待其他所有线程执行完毕后再继续执行的场景，可以用于主线程等待多个工作线程完成任务后再进行后续操作。
 */
public class CyclicBarrierVSCountDownLatch {
}
