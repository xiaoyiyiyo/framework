package com.xiaoyiyiyo.aop.advisor;

import java.lang.reflect.Method;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public interface AfterRunningAdvice extends Advice{

    Object after(Object returnValue, Method method, Object[] args, Object target);
}
