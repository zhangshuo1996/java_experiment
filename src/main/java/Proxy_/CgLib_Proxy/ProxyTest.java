package Proxy_.CgLib_Proxy;

import Proxy_.User;
import net.sf.cglib.proxy.Enhancer;

public class ProxyTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(new UserInterceptor());
        User user = (User)enhancer.create();
        user.eat("葡萄");
    }

}
