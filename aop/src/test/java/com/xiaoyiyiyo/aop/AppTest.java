package com.xiaoyiyiyo.aop;


import com.xiaoyiyiyo.aop.core.AopApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String args[]) throws Exception {
        AopApplicationContext aopApplicationContext = new AopApplicationContext("application.json");
        aopApplicationContext.init();

        TestService testService = (TestService) aopApplicationContext.getBean("testServiceProxy");
        testService.testMethod();
    }
}
