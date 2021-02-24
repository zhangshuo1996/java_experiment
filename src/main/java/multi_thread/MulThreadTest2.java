package multi_thread;

/**
 * 两个线程交替打印123456和ABCDEF
 */
public class MulThreadTest2 {
    public static void main(String[] args) {
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
}
