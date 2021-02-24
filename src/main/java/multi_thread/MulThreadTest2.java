package multi_thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印123456和ABCDEF
 */
public class MulThreadTest2 {
    public static void main(String[] args) {
//        test1(); // 利用wait 和 notify 实现线程之间的通信
        test2(); // 用AtomicInteger 修饰静态变量，这种变量是原子操作。
    }

    /**
     * 利用wait 和 notify 实现线程之间的通信
     */
    private static void test1() {
        final Object o = new Object();
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();
        new Thread(() ->{
            synchronized (o){
                for(char c : aI){
                    System.out.println(Thread.currentThread().getName() + "  --  " + c);
                    try{
                        o.notify(); // 唤醒等待锁的一个线程
                        o.wait(); // 这个线程将锁让出去
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 必须，否则无法停止程序
            }
        }, "t1").start();
        new Thread(() ->{
            synchronized (o){
                for(char c : aC){
                    System.out.println(Thread.currentThread().getName() + "  --  " + c);
                    try{
                        o.notify(); // 唤醒等待锁的一个线程
                        o.wait(); // 这个线程将锁让出去
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 必须，否则无法停止程序
            }
        }, "t2").start();
    }

    /**
     * 利用AtomicInteger 修饰静态变量，这种变量是原子操作。 此种操作不好
     */
    static AtomicInteger threadNo = new AtomicInteger(1);
    private static void test2() {
        final Object o = new Object();
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();
        new Thread(() -> {
            for(char c : aI){
                while(threadNo.get() != 1);
                System.out.println(Thread.currentThread().getName() + " -- " + c);
                threadNo.set(2);
            }
        }, "t1").start();

        new Thread(() -> {
            for(char c : aC){
                while(threadNo.get() != 2);
                System.out.println(Thread.currentThread().getName() + " -- " + c);
                threadNo.set(1);
            }
        }, "t2").start();
    }

}
