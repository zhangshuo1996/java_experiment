package multi_thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 让多个线程顺序执行
 * 多种方法
 * 1. join
 * 2. countDownLatch
 *3. 线程池 中的 newSingleThreadPool
 */
public class ThreadOrderExec {
    public static void main(String[] args) {
//        method1();
//        method2();
        method3();
    }

    public static  void method1(){
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1 执行");
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join(); // join方法使得当前线程阻塞，等待指定线程执行完毕后才会执行
                    System.out.println("线程2 执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                    System.out.println("线程3 执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void method2(){
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1 执行");
                countDownLatch1.countDown();
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch1.await(); // 当countDownLatch1中的参数为0 时，会触发执行
                    System.out.println("线程2 执行");
                    countDownLatch2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch2.await();
                    System.out.println("线程3 执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void method3(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1 执行");
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2 执行");
            }
        });

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程3 执行");
            }
        });
        executorService.submit(thread1);
        executorService.submit(thread2);
        executorService.submit(thread3);
        executorService.shutdown();
    }
}
