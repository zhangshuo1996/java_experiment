package ConstPool;

/**
 * 字符串常量池 TODO: 未完
 * https://blog.csdn.net/L__ear/article/details/100783713
 */
public class ConstPoolTest1 {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
        final String s = "aaa"; // 会放到常量池中
        String[] strs = new String[2];
//        strs[0] = "aaa";
        strs[0] = new String("aaa");
        strs[1] = s;
        System.out.println(strs[0] == strs[1]);
        System.out.println(strs[0].hashCode());
        System.out.println(strs[1].hashCode());
    }

    private static void test2() {
        String s1 = "ab";
        String s2 = "a" + "b";
        System.out.println(s1 == s2); // true

        String s3 = "ab";
        String a1 = "a";
        String s4 = a1 + "b";
        System.out.println(s3 == s4); // false

        String s5 = "ab";
        final String a2 = "a";
        String s6 = a2 + "b";
        System.out.println(s5 == s6); // true
    }



}
