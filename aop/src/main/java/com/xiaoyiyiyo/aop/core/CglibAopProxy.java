package com.xiaoyiyiyo.aop.core;

import com.xiaoyiyiyo.aop.advisor.AdvisorSupport;
import com.xiaoyiyiyo.ioc.utils.ClassUtils;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by xiaoyiyiyo on 2018/3/11.
 */
public class CglibAopProxy implements AopProxy {

    private AdvisorSupport advisorSupport;

    private Object[] constructorArgs;

    private Class<?>[] constructorArgTypes;

    public CglibAopProxy(AdvisorSupport advisorSupport) {
        this.advisorSupport = advisorSupport;
    }

    public CglibAopProxy(AdvisorSupport advisorSupport, Object[] constructorArgs, Class<?>[] constructorArgTypes) {
        this.advisorSupport = advisorSupport;
        this.constructorArgs = constructorArgs;
        this.constructorArgTypes = constructorArgTypes;
    }

    public AdvisorSupport getAdvisorSupport() {
        return advisorSupport;
    }

    public void setAdvisorSupport(AdvisorSupport advisorSupport) {
        this.advisorSupport = advisorSupport;
    }

    public Object[] getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(Object[] constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Class<?>[] getConstructorArgTypes() {
        return constructorArgTypes;
    }

    public void setConstructorArgTypes(Class<?>[] constructorArgTypes) {
        this.constructorArgTypes = constructorArgTypes;
    }

    public Object getProxy() {
        return getProxy(null);
    }

    public Object getProxy(ClassLoader classLoader) {
        Class<?> rootClass = advisorSupport.getTargetSource().getTargetCLass();

        if (classLoader == null) {
            classLoader = ClassUtils.getDefultClassLoader();
        }

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(rootClass.getSuperclass());
        Callback callback = getCallBack(advisorSupport);
        enhancer.setCallback(callback);
        enhancer.setClassLoader(classLoader);

        if (constructorArgs != null && constructorArgs.length > 0) {
            return enhancer.create(constructorArgTypes, constructorArgs);
        }

        return enhancer.create();
    }

    private Callback getCallBack(AdvisorSupport advisorSupport) {
        return new DynamicAdvisedInterceptor(advisorSupport.getList(), advisorSupport.getTargetSource());
    }
}
