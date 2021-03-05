package Proxy_.StaticProxy;

import Proxy_.User;
import Proxy_.UserImpl;

public class UserProxy implements User {
    User user = new UserImpl();

    @Override
    public void eat(String s) {
        System.out.println("静态代理前置内容");
        user.eat(s);
        System.out.println("静态代理后置内容");
    }
}