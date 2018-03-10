package com.xiaoyiyiyo.advisor;

import java.lang.reflect.Method;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public interface AfterRunningAdvice extends Advice{

    Object after(Object retureValue, Method method, Object[] args, Object target);
}
