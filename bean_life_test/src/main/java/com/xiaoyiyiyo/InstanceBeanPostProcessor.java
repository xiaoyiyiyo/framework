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
        System.out.println("2. ----> InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation; " +
                "param aClass.getName = " + aClass.getName() + " ; param s = " + s);
        /*try {
            return Class.forName(""+aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        return null;// TODO
    }

    public boolean postProcessAfterInstantiation(Object o, String s) throws BeansException {
        if (o instanceof Hello) {
            Hello hello = (Hello) o;
            String old = hello.getMessage();
            hello.setMessage("postProcessAfterInstantiation world;");
            System.out.println("4, ----> InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation;" +
                    "param Object o.toString() = " + o.toString() + " ; param s = " + s);
            System.out.println("        change attr: old = " + old +"; new = " + hello.getMessage());
        }
        return true;
    }

    public PropertyValues postProcessPropertyValues(PropertyValues propertyValues,
                                                    PropertyDescriptor[] propertyDescriptors,
                                                    Object o, String s) throws BeansException
    {
        if (o instanceof Hello) {
            Hello hello = (Hello)o;
            String old = hello.getMessage();
            hello.setMessage("postProcessPropertyValues_Hello world;   ---> 不影响之后xml property设值");
            System.out.println("5. ----> InstantiationAwareBeanPostProcessor.postProcessPropertyValues, beanName = "
                    + s + " Attr list : " + Arrays.toString(propertyValues.getPropertyValues()));
            System.out.println("        change attr: old = " + old +"; new = " + hello.getMessage());
        }
        return propertyValues;
    }

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (o instanceof Hello) {
            Hello hello = (Hello) o;
            String old = hello.getMessage();
            hello.setMessage("InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization world.");
            System.out.println("9. ----> InstantiationAwareBeanPostProcessor.postProcessBeforeInitialization.");
            System.out.println("        change attr: old = " + old +"; new = " + hello.getMessage());
        }
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("13. ----> InstantiationAwareBeanPostProcessor.postProcessAfterInitialization.");
        return o;
    }
}
