package com.xiaoyiyiyo.aop.core.jdk;

import com.xiaoyiyiyo.aop.advisor.TargetSource;
import com.xiaoyiyiyo.aop.interceptor.AopMethodInterceptor;
import com.xiaoyiyiyo.aop.invocation.CglibMethodInvocation;
import com.xiaoyiyiyo.aop.invocation.MethodInvocation;
import com.xiaoyiyiyo.aop.invocation.ReflectionMethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by xiaoyiyiyo on 2018/5/15.
 * 用于Jdk proxy
 */
public class DynamicInvocationHandler implements InvocationHandler {

    protected final List<AopMethodInterceptor> interceptorList;

    protected final TargetSource targetSource;

    public DynamicInvocationHandler(List<AopMethodInterceptor> interceptorList, TargetSource targetSource) {
        this.interceptorList = interceptorList;
        this.targetSource = targetSource;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInvocation invocation = new ReflectionMethodInvocation(proxy, targetSource.getTargetObject(),
                method, args, interceptorList);
        return invocation.proceed();
    }
}
