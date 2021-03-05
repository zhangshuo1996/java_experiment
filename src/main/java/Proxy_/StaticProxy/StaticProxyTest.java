package Proxy_.StaticProxy;

public class StaticProxyTest {
    public static void main(String[] args) {
        UserProxy proxy = new UserProxy();
        proxy.eat("苹果");
    }
}
