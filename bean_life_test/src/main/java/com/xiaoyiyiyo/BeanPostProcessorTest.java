package com.xiaoyiyiyo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by xiaoyiyiyo on 2018/4/3.
 */
public class BeanPostProcessorTest implements BeanPostProcessor{
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (o instanceof  Hello) {
            Hello hello = (Hello)o;
            String old = hello.getMessage();
            hello.setMessage("beanPostProcessor_helloWorld");
            System.out.println("8. ----> BeanPostProcessor.postProcessBeforeInitialization, bean = " + o.getClass()
                    + "param s = " + s);
            System.out.println("        change attr: old = " + old + "; new = " + ((Hello) o).getMessage());
        }
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("12. ----> BeanPostProcessor.postProcessAfterInitialization, bean = " + o.getClass()
                + "param s = " + s);
        return o;
    }
}
