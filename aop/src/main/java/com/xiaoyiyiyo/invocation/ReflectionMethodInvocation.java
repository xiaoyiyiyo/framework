package com.xiaoyiyiyo.invocation;

import com.xiaoyiyiyo.interceptor.AopMethodInterceptor;
import com.xiaoyiyiyo.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class ReflectionMethodInvocation implements ProxyMethodInvocation {

    protected final Object proxy;

    protected final Object target;

    protected final Method method;

    protected Object[] arguments = new Object[0];

    protected final List<AopMethodInterceptor> interceptorList;

    private int currentInterceptorIndex = -1;

    public ReflectionMethodInvocation(Object proxy, Object target, Method method, Object[] arguments,
                                      List<AopMethodInterceptor> interceptorList) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.interceptorList = interceptorList;
    }


    public Object getProxy() {
        return proxy;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object proceed() throws Throwable {

        if (currentInterceptorIndex == this.interceptorList.size() - 1) {
            return invokeOriginal();
        }

        AopMethodInterceptor interceptor = interceptorList.get(++currentInterceptorIndex);
        return interceptor.invoke(this);
    }

    protected Object invokeOriginal() throws Throwable {
        return ReflectionUtils.invokeMethodUseReflection(target, method, arguments);
    }
}
