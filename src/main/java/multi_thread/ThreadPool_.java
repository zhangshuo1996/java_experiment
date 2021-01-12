package multi_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPool_ {
    public static void main(String[] args) {
//        test_newCachedThreadPool();
//        test_newFixedThreadPool();
//        test_newScheduledThreadPool();
        test_newSingleThreadPool();
    }


    /**
     * 创建一个可以缓存的线程池，如果线程池的长度超过处理需要，则回收，如果不够用就再创建
     */
    public static void test_newCachedThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for(int i = 0; i < 10; i++){
            final int index = i;

            try {
                Thread.sleep(index* 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "----" + index);
                }
            });
        }
    }

    /**
     * 线程池长度固定
     */
    public static void test_newFixedThreadPool(){
        ExecutorService fixedThreadPoll = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 9; i++){
            final int index = i;

            fixedThreadPoll.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "----" + index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 可以定时，周期性执行的线程池
     */
    public static void test_newScheduledThreadPool(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        // 延迟三秒执行
//        scheduledExecutorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + "----" + "delay 3 seconds");
//            }
//        }, 3, TimeUnit.SECONDS);

        // 延迟一秒执行，每三秒执行一次
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "----" + "delay 1 seconds execute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    /**
     * 单线程化的线程池，只会用唯一的工作线程来执行任务， 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     *
     */
    public static void test_newSingleThreadPool(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        for(int i = 0; i < 9; i++){
            final int index = i;

            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "----" + index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
