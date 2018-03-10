package com.xiaoyiyiyo.interceptor;

import com.xiaoyiyiyo.advisor.AfterRunningAdvice;
import com.xiaoyiyiyo.invocation.MethodInvocation;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class AfterRunningAdviceInterceptor implements AopMethodInterceptor{

    private AfterRunningAdvice advice;

    public AfterRunningAdviceInterceptor(AfterRunningAdvice advice) {
        this.advice = advice;
    }

    public Object invoke(MethodInvocation mi) {
        Object returnVal = mi.proceed();
        advice.after(returnVal, mi.getMethod(), mi.getArguments(), mi);
        return returnVal;
    }
}
