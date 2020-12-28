package base;


import java.math.BigDecimal;

/**
 * 阿里巴巴Java开发手册》中提到：
 * 浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals 来判断。
 */
public class BigDecimal_ {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");

        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);


        System.out.println(x); // 0.1
        System.out.println(y); // 0.1
        System.out.println(x.equals(y)); // true
        System.out.println(x == y); //false
    }

    private static void test1() {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a); // 0.10000024
        System.out.println(b); // 0.0999999964
        System.out.println(a == b);//false 输出不是我们想要的结果，因为精度丢失
    }
}
