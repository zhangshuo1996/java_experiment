package JVM_;

import java.util.ArrayList;

/**
 * VM Args：
 * -Xms20m -Xmx20m
 *
 * 运行时需要开启jconsole
 */
public class OutOfMemoryTest {
    static class OOMObject {
        public byte[] byt = new byte[1 * 1024 * 1024];
    }

    public static void main(String[] args) throws Exception {
        Thread.sleep(10000);
        fillHeap(100);
        Thread.sleep(10000);
    }

    public static void fillHeap(int num) throws Exception {
        ArrayList<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(500);
            list.add(new OOMObject());
            System.out.println("num=" + i);
        }
        System.gc();
    }


}