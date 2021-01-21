package multi_thread;

import org.openjdk.jol.info.ClassLayout;

public class Object_info_ {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        Object o = new Object();
        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);
    }

    private static void test2() {
        Object o = new Object();
        synchronized (o) {
            String s = ClassLayout.parseInstance(o).toPrintable();
            System.out.println(s);
        }
    }
}
