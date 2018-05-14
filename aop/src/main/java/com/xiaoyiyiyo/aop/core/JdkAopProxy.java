package com.xiaoyiyiyo.aop.core;

import com.xiaoyiyiyo.aop.advisor.AdvisorSupport;
import com.xiaoyiyiyo.ioc.utils.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by xiaoyiyiyo on 2018/5/14.
 */
public class JdkAopProxy implements AopProxy{

    private AdvisorSupport advisorSupport;

    public JdkAopProxy(AdvisorSupport advisorSupport) {
        this.advisorSupport = advisorSupport;
    }

    public AdvisorSupport getAdvisorSupport() {
        return advisorSupport;
    }

    public void setAdvisorSupport(AdvisorSupport advisorSupport) {
        this.advisorSupport = advisorSupport;
    }


    @Override
    public Object getProxy() {
        return getProxy(null);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        Class<?> rootClass = advisorSupport.getTargetSource().getTargetCLass();

        if (classLoader == null) {
            classLoader = ClassUtils.getDefultClassLoader();
        }

        return Proxy.newProxyInstance(classLoader, rootClass.getInterfaces(), getInvocationHandler(advisorSupport));
    }

    private InvocationHandler getInvocationHandler(AdvisorSupport advisorSupport) {
        return new DynamicInvocationHandler(advisorSupport.getList(), advisorSupport.getTargetSource());
    }
}
