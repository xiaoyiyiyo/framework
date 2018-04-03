package com.xiaoyiyiyo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by xiaoyiyiyo on 2018/4/3.
 */
public class BeanPostProcessorTest implements BeanPostProcessor{
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("BeanPostProcessor.postProcessBeforeInitialization, bean = " + o.getClass()
            + "param s = " + s);
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("BeanPostProcessor.postProcessAfterInitialization, bean = " + o.getClass()
                + "param s = " + s);
        return o;
    }
}
