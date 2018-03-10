package com.xiaoyiyiyo.interceptor;

import com.xiaoyiyiyo.invocation.MethodInvocation;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public interface AopMethodInterceptor {

    Object invoke(MethodInvocation mi);
}
