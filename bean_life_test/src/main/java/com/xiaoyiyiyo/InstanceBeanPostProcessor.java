package com.xiaoyiyiyo;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * Created by xiaoyiyiyo on 2018/4/3.
 */
public class InstanceBeanPostProcessor implements InstantiationAwareBeanPostProcessor{
    public Object postProcessBeforeInstantiation(Class<?> aClass, String s) throws BeansException {
        System.out.println("----> InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation; " +
                "aClass.getName = " + aClass.getName() + " ; param s = " + s);
        /*try {
            return Class.forName(""+aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        return null;// TODO
    }

    public boolean postProcessAfterInstantiation(Object o, String s) throws BeansException {
        System.out.println("----> InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation;");
        return true;
    }

    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues,
                                                    PropertyDescriptor[] propertyDescriptors,
                                                    Object o, String s) throws BeansException
    {
        System.out.println("----> InstantiationAwareBeanPostProcessor.postProcessPropertyValues, beanName = "
                + s + " Attr list : " + Arrays.toString(propertyValues.getPropertyValues()));
        return null;
    }

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("----> InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization.");
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("----> InstantiationAwareBeanPostProcessor.postProcessAfterInitialization.");
        return o;
    }
}
