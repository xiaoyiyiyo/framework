package com.xiaoyiyiyo.aop;

/**
 * Created by xiaoyiyiyo on 2018/3/13.
 */
public class TestService {
    public String testMethod(String message) throws InterruptedException {
        System.out.println("message = " + message);
        return message;
    }
}
