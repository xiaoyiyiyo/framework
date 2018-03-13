package com.xiaoyiyiyo.aop.core;

import com.xiaoyiyiyo.aop.advisor.*;
import com.xiaoyiyiyo.aop.bean.AopBeanDefinition;
import com.xiaoyiyiyo.aop.interceptor.AfterRunningAdviceInterceptor;
import com.xiaoyiyiyo.aop.interceptor.BeforeRunningAdviceInterceptor;
import com.xiaoyiyiyo.ioc.core.BeanFactoryImpl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaoyiyiyo on 2018/3/11.
 */
public class AopBeanFactoryImpl extends BeanFactoryImpl {

    private static final ConcurrentHashMap<String, AopBeanDefinition> aopBeanDefinitionMap =
            new ConcurrentHashMap<String, AopBeanDefinition>();

    private static final ConcurrentHashMap<String, Object> aopBeanMap = new ConcurrentHashMap<String, Object>();

    @Override
    public Object getBean(String name) throws Exception {
        Object aopBean = aopBeanMap.get(name);

        if (aopBean != null) {
            return aopBean;
        }

        if (aopBeanDefinitionMap.containsKey(name)) {
            AopBeanDefinition aopBeanDefinition = aopBeanDefinitionMap.get(name);
            AdvisorSupport advisorSupport = getAdvisorSupport(aopBeanDefinition);
            aopBean = new CglibAopProxy(advisorSupport).getProxy();
            return aopBean;
        }
        return super.getBean(name);
    }

    protected void registerBean(String name, AopBeanDefinition aopBeanDefinition) {
        aopBeanDefinitionMap.put(name, aopBeanDefinition);
    }

    public AdvisorSupport getAdvisorSupport(AopBeanDefinition aopBeanDefinition) throws Exception {
        AdvisorSupport advisorSupport = new AdvisorSupport();
        List<String> interceptorNames = aopBeanDefinition.getInterceptorNames();
        if (interceptorNames != null && !interceptorNames.isEmpty()) {
            for (String interceptorName : interceptorNames) {
                Advice advice = (Advice) getBean(interceptorName);

                if (advice instanceof BeforeRunningAdvice) {
                    advisorSupport.addAopMethodInterceptor(
                            new BeforeRunningAdviceInterceptor((BeforeRunningAdvice) advice));
                }

                if (advice instanceof AfterRunningAdvice) {
                    advisorSupport.addAopMethodInterceptor(
                            new AfterRunningAdviceInterceptor((AfterRunningAdvice) advice));
                }
            }
        }

        TargetSource targetSource = new TargetSource();
        Object object = getBean(aopBeanDefinition.getTarget());
        targetSource.setTargetCLass(object.getClass());
        targetSource.setTargetObject(object);

        advisorSupport.setTargetSource(targetSource);
        return advisorSupport;
    }
}
