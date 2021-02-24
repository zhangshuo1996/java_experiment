package JVM_;

public class T_class_loader {
    static {
        // 类加载的时候先执行静态代码块中的
        i = 0;
    }
    static int i = 1;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
