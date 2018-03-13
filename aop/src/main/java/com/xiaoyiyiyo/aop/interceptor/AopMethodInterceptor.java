package com.xiaoyiyiyo.aop.interceptor;

import com.xiaoyiyiyo.aop.invocation.MethodInvocation;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public interface AopMethodInterceptor {

    Object invoke(MethodInvocation mi) throws Throwable;
}
