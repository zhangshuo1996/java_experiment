package base;

public class t_final {
    public static void main(String[] args) {
        int n1 = 2019;          //普通变量
        final int n2 = 2019;    //final修饰的变量
        n1 = n2;
        String s = "20190522";
        String s1 = n1 + "0522";	//拼接字符串"20190512"
        String s2 = n2 + "0522";

        System.out.println(s == s1);	//false
        System.out.println(s == s2);	//true
        /**
         * 上面的代码运作过程是这样的:
         * 首先根据final修饰的常量会在编译期放到常量池的原则, n2会在编译期间放到常量池中.
         * 然后s变量所对应的"20190522"字符串会放入到字符串常量池中, 并对外提供一个引用返回给s变量.
         * 这时候拼接字符串s1, 由于n1对应的数据没有放入常量池中, 所以s1暂时无法拼接, 需要等程序加载运行时才能确定s1对应的值.
         * 但在拼接s2的时候, 由于n2已经存在于常量池, 所以可以直接与"0522"拼接, 拼接出的结果是"20190522". 这时系统会查看字符串常量池,
         * 发现已经存在字符串20190522, 所以直接返回20190522的引用. 所以s2和s指向的是同一个引用, 这个引用指向的是字符串常量池中的20190522.
         */
        final int a = 1;
        int b = a;
        b = 2;

        System.out.println(a);
        System.out.println(b);

    }
}
