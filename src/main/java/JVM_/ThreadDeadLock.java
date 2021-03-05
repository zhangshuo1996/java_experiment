package JVM_;

public class ThreadDeadLock implements Runnable{

    //控制锁顺序
    private boolean lockFormer;
    //对象1
    private static Object o1 = new Object();
    //对象2
    private static Object o2 = new Object();

    ThreadDeadLock(boolean lockFormer) {
        this.lockFormer = lockFormer;
    }

    @Override
    public void run() {
        if (this.lockFormer) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1ok");
                }
            }
        } else {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("2ok");
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            new Thread(new ThreadDeadLock(true)).start();
            new Thread(new ThreadDeadLock(false)).start();
        }
    }

}
