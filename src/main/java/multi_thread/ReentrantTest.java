package multi_thread;

/**
 * 测试可重入锁
 *
 */
public class ReentrantTest {
    public static synchronized void print(int i) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + i);
        Thread.sleep(1000);
    }

    public static void main(String[] args) {
        for(int i = 0; i <= 1; i++){
            new Thread(() -> {
                try {
                    print(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
