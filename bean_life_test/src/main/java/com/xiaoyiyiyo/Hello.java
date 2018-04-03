package com.xiaoyiyiyo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Created by xiaoyiyiyo on 2018/4/3.
 */
public class Hello implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String message;;

    public Hello() {
        System.out.println("Construct :  hello world");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void xml_init() {
        System.out.println("----> xml_init");
    }

    public void xml_destroy() {
        System.out.println("----> xml_destroy");
    }

    public void setBeanName(String s) {
        System.out.println("----> BeanNameAware.setBeanName : param s = " + s);
    }


    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("----> BeanFactoryAware.setBeanFactory : param beanFactory = " + beanFactory.toString());
    }


    public void afterPropertiesSet() throws Exception {
        System.out.println("----> InitializingBean.afterPropertiesSet");
    }


    public void destroy() throws Exception {
        System.out.println("----> DisposableBean.destroy");
    }
}
