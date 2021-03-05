package collection_;

import java.util.concurrent.ConcurrentHashMap;

public class T_ConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        for(int i = 0; i <= 2; i++){
            new MulThreadWrite(concurrentHashMap).start();;
        }
    }


}

class MulThreadWrite extends Thread{

    ConcurrentHashMap concurrentHashMap;
    MulThreadWrite(ConcurrentHashMap<String, String> map){
        concurrentHashMap = map;
    }

    @Override
    public void run() {
        write();
    }

    public void write(){
        for(int i = 0; i <= 50; i++){
            concurrentHashMap.put(Thread.currentThread().getName(), "  hah " + i);
        }
    }
}
