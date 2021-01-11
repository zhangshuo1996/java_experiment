package multi_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateThread {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    private static void test1() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "  : " + i);
            if (i == 50) {
                new FirstThreadTest().start();
                new FirstThreadTest().start();
            }
        }
    }

    /**
     * 方法1 ： 继承Thread类， 重写 run 方法
     */
    public static class FirstThreadTest extends Thread {

        int i = 0;

        //重写run方法，run方法的方法体就是现场执行体
        public void run() {
            for (; i < 100; i++) {
                System.out.println(getName() + "  " + i);
            }
        }


    }

    private static void test2() {
        for(int i = 0;i < 100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" "+i);
            if(i==20)
            {
                RunnableThreadTest rtt = new RunnableThreadTest();
                new Thread(rtt,"新线程1").start();
                new Thread(rtt,"新线程2").start();
            }
        }
    }

    /**
     * 方法2： 实现runnable接口，重写run方法
     */
    public static class RunnableThreadTest implements Runnable{
        private int i;
        public void run()
        {
            for(i = 0;i <100;i++)
            {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
        }
    }

    private static void test3() {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
            if (i == 20) {
                new Thread(ft, "有返回值的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回值：" + ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过Callable和Future创建线程
     * （1）创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值。
     * （2）创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。
     * （3）使用FutureTask对象作为Thread对象的target创建并启动新线程。
     * （4）调用FutureTask对象的get()方法来获得子线程执行结束后的返回值
     */

    public static class CallableThreadTest implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            int i = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            return i;
        }


    }

    /**
     * 采用实现Runnable、Callable接口的方式创见多线程时，优势是：
         * 线程类只是实现了Runnable接口或Callable接口，还可以继承其他类。
         * 在这种方式下，多个线程可以共享同一个target对象，所以非常适合多个相同线程来处理同一份资源的情况，从而可以将CPU、代码和数据分开，形成清晰的模型，较好地体现了面向对象的思想。
     * 劣势是：
         *  编程稍微复杂，如果要访问当前线程，则必须使用Thread.currentThread()方法。
     * 使用继承Thread类的方式创建多线程时优势是：
         * 编写简单，如果需要访问当前线程，则无需使用Thread.currentThread()方法，直接使用this即可获得当前线程。
     * 劣势是：
         * 线程类已经继承了Thread类，所以不能再继承其他父类。
     */

}
