package base;

public class text_String {
    public static void main(String[] args) {
        String x = "张三";
        String y = "张三";
        String z = new String("张三");
        System.out.println(x == y); // true
        System.out.println(x == z); // false

        /**
         * String x = “张三” 的方式，Java 虚拟机会将其分配到常量池中，
         * 而常量池中没有重复的元素，比如当执行“张三”时，java虚拟机会先在常量池中检索是否已经有“张三”,
         * 如果有那么就将“张三”的地址赋给变量，
         * 如果没有就创建一个，然后在赋给变量；
         * 而 String z = new String(“张三”) 则会被分到堆内存中，即使内容一样还是会创建新的对象。
         */

        String s1="Hello";
        String s2="Hello";
        String s3=new String("Hello");
        System.out.println("s1和s2 引用地址是否相同："+(s1 == s2)); // true;
        System.out.println("s1和s2 值是否相同："+s1.equals(s2)); // true;
        System.out.println("s1和s3 引用地址是否相同："+(s1 == s3));// false
        System.out.println("s1和s3 值是否相同："+s1.equals(s3));// true;
    }
}
