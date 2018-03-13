package com.xiaoyiyiyo.aop.interceptor;

import com.xiaoyiyiyo.aop.advisor.BeforeRunningAdvice;
import com.xiaoyiyiyo.aop.invocation.MethodInvocation;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class BeforeRunningAdviceInterceptor implements AopMethodInterceptor {

    private BeforeRunningAdvice advice;

    public BeforeRunningAdviceInterceptor(BeforeRunningAdvice advice) {
        this.advice = advice;
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        advice.before(mi.getMethod(), mi.getArguments(), mi);
        return mi.proceed();
    }
}
