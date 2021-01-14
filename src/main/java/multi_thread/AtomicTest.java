package multi_thread;

import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    private static Logger log = Logger.getLogger(AtomicTest.class);

    public static void main(String[] args) throws Exception {
//        atomic_integer_test();
        atomic_boolean_test();
    }

    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    /**
     * 对AtomicInteger进行测试
     * @throws InterruptedException
     */
    public static void atomic_integer_test() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();//获取线程池
        final Semaphore semaphore = new Semaphore(threadTotal);//定义信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("exception" + e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count:{}" + count.get());
    }

    private static void add() {
        count.incrementAndGet();
    }

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    /**
     * 测试atomicBoolean
     * @throws InterruptedException
     */
    public static void atomic_boolean_test() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("--------------isHappened:{}" + isHappened.get());
        /**
         执行之后发现，log.info("execute");只执行了一次，且isHappend值为true。
         原因就是当它第一次compareAndSet()之后，isHappend变为true，没有别的线程干扰。
         通过使用AtomicBoolean，我们可以使某段代码只执行一次。
         */

    }

    private static void test() {
        if (isHappened.compareAndSet(false, true)) {
            log.info("---------------my execute");
        }
    }

}
