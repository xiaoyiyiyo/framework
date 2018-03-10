package com.xiaoyiyiyo.invocation;

import java.lang.reflect.Method;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public interface MethodInvocation {

    Method getMethod();

    Object[] getArguments();

    Object proceed();
}
