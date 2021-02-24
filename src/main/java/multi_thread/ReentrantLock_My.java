package multi_thread;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock_My implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();//创建锁对象
    private int thold;

    public ReentrantLock_My(int h) {
        this.thold = h;
    }

    public static void main(String[] args) {
        for (int i = 10; i < 15; i++) {
            new Thread(new ReentrantLock_My(i),"name-" + i).start();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            lock.lock(); //获取锁
            System.out.println("lock threadName:" + Thread.currentThread().getName());
            {
                System.out.println(" writeStart ");
                for (int i = 0; i < 15; i++) {
                    Thread.sleep(100);
                    System.out.print(thold+",");
                }
                System.out.println(" writeEnd");
            }
            System.out.println("unlock threadName:" + Thread.currentThread().getName() + "\r\n");
            lock.unlock(); //锁释放
        } catch (InterruptedException e) {}
    }

}
