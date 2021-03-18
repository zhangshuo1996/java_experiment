package multi_thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition_ {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {

    }
}

class ConditionUsage{
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void aGuareMethod(){
        lock.lock();
        try{
            while(true){ // TODO: 设置保护条件
                condition.await();
            }
            // TODO: 执行目标操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void anNotificationMethod(){
        lock.lock();
        try{
            changeState();
            condition.signal(); // 唤醒任意一个
        }finally {
            lock.unlock();
        }
    }

    private void changeState() {
    }
}