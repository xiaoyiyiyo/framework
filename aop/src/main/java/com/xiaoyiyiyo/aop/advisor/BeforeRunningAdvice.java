package com.xiaoyiyiyo.aop.advisor;

import java.lang.reflect.Method;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public interface BeforeRunningAdvice extends Advice{

    void before(Method method, Object[] args, Object target);
}