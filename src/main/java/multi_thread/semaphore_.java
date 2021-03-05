package multi_thread;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
     *
     * @author Snailclimb
     * @date 2018年9月30日
     * @Description: 需要一次性拿一个许可的情况
     */

public class semaphore_ {
    // 请求的数量
    private static final int threadCount = 550;

    public static void main(String[] args) throws InterruptedException {
//        test1();
        SemaphoreTest s = new SemaphoreTest();
        s.test();
    }

    private static void test1() {
        // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        // 一次只能允许执行的线程数量。
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    semaphore.acquire();// 获取一个许可，所以可运行线程数量为20/1=20
                    test(threadnum);
                    semaphore.release();// 释放一个许可
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }


    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("thread_id" + Thread.currentThread().getName() + "   threadnum:" + threadnum);
        Thread.sleep(1000);// 模拟请求的耗时操作
    }
}

class SemaphoreTest {
    private static final int COUNT = 40;
    private static Executor executor = Executors.newFixedThreadPool(COUNT);
    private static Semaphore semaphore = new Semaphore(10);
    public void test() {
        for (int i=0; i< COUNT; i++) {
            executor.execute(new Task());
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                //读取文件操作
                System.out.println(Thread.currentThread().getName() + "读取文件");
                semaphore.acquire();
                // 存数据过程
                System.out.println(Thread.currentThread().getName() + "存储文件");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}


