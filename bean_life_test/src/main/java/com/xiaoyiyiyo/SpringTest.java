package com.xiaoyiyiyo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xiaoyiyiyo on 2018/4/3.
 */
public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application*.xml");
        Hello obj = (Hello)context.getBean("hello");
        System.out.println("Bean is working. Message = " + obj.getMessage());
        ((ClassPathXmlApplicationContext)context).close();
    }
}
