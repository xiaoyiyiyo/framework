package com.xiaoyiyiyo.core;

import com.xiaoyiyiyo.advisor.TargetSource;
import com.xiaoyiyiyo.interceptor.AopMethodInterceptor;
import com.xiaoyiyiyo.invocation.CglibMethodInvocation;
import com.xiaoyiyiyo.invocation.MethodInvocation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by xiaoyiyiyo on 2018/3/11.
 */
public class DynamicAdvisedInterceptor implements MethodInterceptor {

    protected final List<AopMethodInterceptor> interceptorList;

    protected final TargetSource targetSource;

    public DynamicAdvisedInterceptor(List<AopMethodInterceptor> interceptorList, TargetSource targetSource) {
        this.interceptorList = interceptorList;
        this.targetSource = targetSource;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MethodInvocation invocation = new CglibMethodInvocation(o, targetSource.getTargetObject(),
                method, objects, interceptorList, methodProxy);
        return invocation.proceed();
    }
}
