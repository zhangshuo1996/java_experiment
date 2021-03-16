package multi_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Wait 和 Notify 应用场景测试 生产者 和 消费者模式
 *
 */
public class WaitAndNotifyTest {
    static class Producer implements Runnable{
        private final List<Integer> container;

        public Producer(List<Integer> container){
            this.container = container;
        }

        /**
         * 生产者方法
         */
        private void produce() throws InterruptedException {
            int capacity = 5;
            synchronized (container){
                // 当前容器已满，停止生产
                while(container.size() == capacity){ // 为啥子要用while？？，不能用if 呢？
                    System.out.println("容器已满，生产停止");
                    container.wait(); // 释放锁， 让出CPU， 然后呢？ 生产者消费者线程会咋样？ 当前线程暂停执行
                }
                Random random = new Random();
                int p = random.nextInt(50);
                TimeUnit.MILLISECONDS.sleep(1000);
                container.add(p);
                System.out.println("生产了一个产品  " + p);
                container.notifyAll();
            }
        }

        @Override
        public void run(){
            while(true){
                try{
                    produce();
                }catch (InterruptedException e){
                    e.printStackTrace();
                    System.out.println("Producer error");
                }
            }
        }
    }

    static class Consumer implements Runnable{
        private final List<Integer> container;

        public Consumer(List<Integer> container){
            this.container = container;
        }

        /**
         * 消费者进行消费
         */
        private void consume() throws InterruptedException {
            synchronized (container){
                while(container.isEmpty()){
                    System.out.println("容器为null，消费停止");
                    container.wait();
                }
                Integer p = container.remove(0);
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("消费产品" + p);
                container.notifyAll();
            }
        }

        @Override
        public void run(){
            while(true){
                try{
                    consume();
                }catch (InterruptedException e){
                    e.printStackTrace();
                    System.out.println("消费异常");
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> container = new ArrayList<>();
        Thread producer = new Thread(new Producer(container));
        Thread consumer = new Thread(new Consumer(container));

        producer.start();
        consumer.start();
    }

}

class WaitNotify {

    final static Object lock = new Object();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程 A 等待拿锁");
                synchronized (lock) {
                    try {
                        System.out.println("线程 A 拿到锁了");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("线程 A 开始等待并放弃锁");
                        lock.wait();
                        System.out.println("被通知可以继续执行 则 继续运行至结束");
                    } catch (InterruptedException e) {
                    }
                }
            }
        }, "线程 A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程 B 等待锁");
                synchronized (lock) {
                    System.out.println("线程 B 拿到锁了");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                    }
                    lock.notify();
                    System.out.println("线程 B 随机通知 Lock 对象的某个线程");
                }
            }
        }, "线程 B").start();
    }


}

