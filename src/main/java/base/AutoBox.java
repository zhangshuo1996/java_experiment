package base;

/**
 * 自动装箱 与自动拆箱
 */
public class AutoBox {
    public static void main(String[] args) {
        test2();
    }
    public static void test1(){
        Integer i1 = new Integer(1);
        int i2 = 1;
        System.out.println(i1 == i2); // true

        Integer i3 = new Integer(28888);
        int i4 = 28888;
        System.out.println(i3 == i4);
    }

    public static void test2(){
        Integer i1 = new Integer(1); // new 的时候会创建新对象
        Integer i2 = new Integer(1);
        System.out.println(i1 == i2); // false

        Integer i3 = 1; // -127 -- 128的时候会直接从常量池中取对象
        Integer i4 = 1;
        System.out.println(i3 == i4); //true

        Integer i5 = 128;
        Integer i6 = 128;
        System.out.println(i5 == i6); //false

        Integer i7 = new Integer(40);
        Integer i8 = new Integer(40);
        Integer i9 = new Integer(0);
        System.out.println(i7 == i8+i9);  // true
        /**
         * 语句 i7 == i8 + i9，因为+这个操作符不适用于 Integer 对象，首先 i8 和 i9 进行自动拆箱操作，进行数值相加，即 i7 == 40。
         * 然后 Integer 对象无法与数值进行直接比较，所以 i7 自动拆箱转为 int 值 40，最终这条语句转为 40 == 40 进行数值比较。
         */
    }
}
