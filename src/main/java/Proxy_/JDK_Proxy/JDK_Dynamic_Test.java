package Proxy_.JDK_Proxy;
import Proxy_.User;
import Proxy_.UserImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDK_Dynamic_Test {

    public static void main(String[] args) {
        User user = new UserImpl();
        InvocationHandler h = new JDKDynamicProxy(user);
        User proxy = (User) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{User.class}, h);
        proxy.eat("苹果");
    }
}
