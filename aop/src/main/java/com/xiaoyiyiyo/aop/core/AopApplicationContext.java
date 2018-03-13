package com.xiaoyiyiyo.aop.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaoyiyiyo.aop.bean.AopBeanDefinition;
import com.xiaoyiyiyo.ioc.bean.BeanDefinition;
import com.xiaoyiyiyo.aop.bean.ProxyFactoryBean;
import com.xiaoyiyiyo.ioc.utils.ClassUtils;
import com.xiaoyiyiyo.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * Created by xiaoyiyiyo on 2018/3/13.
 */
public class AopApplicationContext extends AopBeanFactoryImpl{

    private String fileName;

    public AopApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<AopBeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<AopBeanDefinition>>() {});

        if (beanDefinitions != null && !beanDefinitions.isEmpty()) {
            for (AopBeanDefinition aopBeanDefinition : beanDefinitions) {
                Class<?> clz = ClassUtils.loadClass(aopBeanDefinition.getClassName());
                if(clz == ProxyFactoryBean.class){
                    registerBean(aopBeanDefinition.getName(), aopBeanDefinition);
                }else {
                    registerBean(aopBeanDefinition.getName(),(BeanDefinition) aopBeanDefinition);
                }
            }
        }
    }
}
