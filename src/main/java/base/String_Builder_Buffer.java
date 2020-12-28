package base;

/**
 * String 类中使用 final 关键字修饰字符数组来保存字符串，private final char value[]，所以String 对象是不可变的
 * 补充（来自issue 675）：在 Java 9 之后，String 类的实现改用 byte 数组存储字符串 private final byte[] value;
 *
 * 而 StringBuilder 与 StringBuffer 都继承自 AbstractStringBuilder 类，
 * 在 AbstractStringBuilder 中也是使用字符数组保存字符串char[]value 但是没有用 final 关键字修饰，所以这两种对象都是可变的。
 *
 * StringBuilder 与 StringBuffer 的构造方法都是调用父类构造方法也就是AbstractStringBuilder 实现的
 */

public class String_Builder_Buffer {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {

    }


}
