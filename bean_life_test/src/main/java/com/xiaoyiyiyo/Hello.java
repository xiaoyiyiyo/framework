package com.xiaoyiyiyo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 * Created by xiaoyiyiyo on 2018/4/3.
 */
public class Hello implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private String message;

    public Hello() {
        this.message = "original hello world";
        System.out.println("3, ----> Construct :  hello world");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void xml_init() {
        String old = message;
        this.message = "xml_init Hello world";

        System.out.println("11. ----> [Hello]: xml_init");
        System.out.println("        change attr: old = " + old +"; new = " + message);
    }

    public void xml_destroy() {
        System.out.println("16. ----> [Hello]: xml_destroy");
    }

    public void setBeanName(String s) {
        System.out.println("6. ----> BeanNameAware.setBeanName : param s = " + s);
    }


    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("7. ----> BeanFactoryAware.setBeanFactory : param beanFactory = " + beanFactory.toString());
    }


    public void afterPropertiesSet() throws Exception {
        System.out.println("10. ----> InitializingBean.afterPropertiesSet");
    }


    public void destroy() throws Exception {
        System.out.println("15. ---->  DisposableBean.destroy");
    }
}
