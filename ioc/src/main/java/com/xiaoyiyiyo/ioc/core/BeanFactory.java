package com.xiaoyiyiyo.ioc.core;

/**
 * Created by xiaoyiyiyo on 2018/3/8.
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
