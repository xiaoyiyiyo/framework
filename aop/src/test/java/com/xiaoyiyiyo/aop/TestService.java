package com.xiaoyiyiyo.aop;

/**
 * Created by xiaoyiyiyo on 2018/3/13.
 */
public class TestService implements ITestService{

    @Override
    public String testMethod(String message) {
        System.out.println("message = " + message);
        return message;
    }
}
