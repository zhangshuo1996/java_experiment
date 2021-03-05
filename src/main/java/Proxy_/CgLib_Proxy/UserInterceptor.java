package Proxy_.CgLib_Proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class UserInterceptor implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib预处理");
        Object object = methodProxy.invokeSuper(o, objects); // 这里有错误??
        System.out.println("Cglib后处理");
        return object;
    }
}
