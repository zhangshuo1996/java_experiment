package multi_thread;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedTest {
    private static Logger log = Logger.getLogger(AtomicTest.class);

    public static void main(String[] args) {
//        test1();
        test2();
    }

    public static void test1(){
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //一
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example1.test1(2);
        });
        //二
        executorService.execute(() -> {
            example2.test2(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
        //三
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example2.test1(2);
        });
    }

    public static void test2(){
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
    }

    static class SynchronizedExample1 {

        // 修饰一个代码块
        public void test1(int j) {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    log.info("test1 -  " + j + "  -  " + i);
                }
            }
        }

        // 修饰一个方法
        public synchronized void test2(int j) {
            for (int i = 0; i < 10; i++) {
                log.info("test2 -  " + j + "  -  " + i);
            }
        }

    }

    static class SynchronizedExample2{
        // 修饰一个类
        public static void test1(int j) {
            synchronized (SynchronizedExample2.class) {
                for (int i = 0; i < 10; i++) {
                    log.info("test3 -  " + j + "  -  " + i);
                }
            }
        }

        /**
         *  修饰一个静态方法
         * @param j
         */
        public static synchronized void test2(int j) {
            for (int i = 0; i < 10; i++) {
                log.info("test4 -  " + j + "  -  " + i);
            }
        }
    }
}
