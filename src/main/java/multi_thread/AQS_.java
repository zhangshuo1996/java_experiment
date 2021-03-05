package multi_thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class AQS_ {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(1);
        ReentrantLock reentrantLock = new ReentrantLock();
        int a = 0, b = 1;
        a = b = 3;
        System.out.println(a + "  " + b);
    }
}
