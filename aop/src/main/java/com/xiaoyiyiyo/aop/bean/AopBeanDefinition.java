package com.xiaoyiyiyo.aop.bean;

import com.xiaoyiyiyo.ioc.bean.BeanDefinition;

import java.util.List;

/**
 * Created by xiaoyiyiyo on 2018/3/11.
 */
public class AopBeanDefinition extends BeanDefinition {

    private String target;

    private List<String> interceptorNames;

    public AopBeanDefinition() {
    }

    public AopBeanDefinition(String target, List<String> interceptorNames) {
        this.target = target;
        this.interceptorNames = interceptorNames;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<String> getInterceptorNames() {
        return interceptorNames;
    }

    public void setInterceptorNames(List<String> interceptorNames) {
        this.interceptorNames = interceptorNames;
    }
}
