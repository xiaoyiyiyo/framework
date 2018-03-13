package com.xiaoyiyiyo.aop.interceptor;

import com.xiaoyiyiyo.aop.advisor.AfterRunningAdvice;
import com.xiaoyiyiyo.aop.invocation.MethodInvocation;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class AfterRunningAdviceInterceptor implements AopMethodInterceptor{

    private AfterRunningAdvice advice;

    public AfterRunningAdviceInterceptor(AfterRunningAdvice advice) {
        this.advice = advice;
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        Object returnVal = mi.proceed();
        advice.after(returnVal, mi.getMethod(), mi.getArguments(), mi);
        return returnVal;
    }
}
