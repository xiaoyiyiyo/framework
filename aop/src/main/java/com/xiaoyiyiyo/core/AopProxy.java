package com.xiaoyiyiyo.core;

/**
 * Created by xiaoyiyiyo on 2018/3/11.
 */
public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
