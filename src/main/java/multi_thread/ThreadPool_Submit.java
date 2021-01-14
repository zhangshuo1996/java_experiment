package multi_thread;

import java.util.concurrent.*;

/**
 * 线程池中的提交方法 submit和execute
 */
public class ThreadPool_Submit {
    public static void main(String[] args) throws Exception{
//        execute_except_runnable_test();
//        execute_submit_runnable_test();
//        execute_submit_callable_test();
        future_get_test();
    }

    /**
     *  线程池 execute(runnable) 测试异常 ，直接抛出
     */
    public static void execute_except_runnable_test(){
        ExecutorService es = Executors.newSingleThreadExecutor();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable线程处理开始...");
                int a = 0;
                int b = 3;

                System.out.println("除以0的结果为：" + b/a); // 抛出异常
                System.out.println("Runnable线程处理结束...");
            }
        };
        es.execute(runnable);
        es.shutdown();
    }


    /**
     *  线程池 submit(runnable) 测试异常 ，不抛出异常
     */
    public static void execute_submit_runnable_test(){
        ExecutorService es = Executors.newSingleThreadExecutor();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable线程处理开始...");
                int a = 0;
                int b = 3;

                System.out.println("除以0的结果为：" + b/a); // 抛出异常
                System.out.println("Runnable线程处理结束...");
            }
        };
        es.submit(runnable);
        es.shutdown();
    }

    /**
     *  线程池 submit(callable) 测试异常 ，有返回值 Future, 调用Future.get()时才会抛出异常
     */
    public static void execute_submit_callable_test() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Callable callable = new Callable() {
            @Override
            public Object call() {
                System.out.println("Runnable线程处理开始...");
                int a = 0;
                int b = 3;

                System.out.println("除以0的结果为：" + b/a); // 抛出异常
                System.out.println("Runnable线程处理结束...");
                return "0";
            }
        };
        Future<String> future = es.submit(callable);
        System.out.println("任务执行完成，结果为：" + future.get());
    }

    /**
     * 测试 Future.get() future的get方法在未获得返回值之前会一直阻塞，我们可以使用future的isDone方法判断任务是否执行完成，然后再决定是否get，
     */
    public static void future_get_test() throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Callable callable = new Callable() {
            @Override
            public String call() throws Exception {
                System.out.println("线程处理开始...");
                int a = 2;
                int b = 3;
                System.out.println("3/2的结果为：" + b/a);
                System.out.println("线程处理结束...");
                return "0";
            }
        };
        Future<String> future = es.submit(callable);
        while(true) {
            //idDone:如果任务已完成，则返回 true。 可能由于正常终止、异常或取消而完成，在所有这些情况中，此方法都将返回 true。
            if(future.isDone()) {
                System.out.println("任务执行完成：" + future.get());
                break;
            }
        }
        es.shutdown();
    }




}
