package com.xiaoyiyiyo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by xiaoyiyiyo on 2018/4/3.
 */
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("1.  ----> BeanFactoryPostProcessor.postProcessBeanFactory");
    }
}
