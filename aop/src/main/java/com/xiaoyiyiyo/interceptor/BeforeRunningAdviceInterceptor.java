package com.xiaoyiyiyo.interceptor;

import com.xiaoyiyiyo.advisor.BeforeRunningAdvice;
import com.xiaoyiyiyo.invocation.MethodInvocation;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class BeforeRunningAdviceInterceptor implements AopMethodInterceptor {

    private BeforeRunningAdvice advice;

    public BeforeRunningAdviceInterceptor(BeforeRunningAdvice advice) {
        this.advice = advice;
    }

    public Object invoke(MethodInvocation mi) {
        advice.before(mi.getMethod(), mi.getArguments(), mi);
        return mi.proceed();
    }
}
