package com.xiaoyiyiyo.aop;

import com.xiaoyiyiyo.aop.advisor.BeforeRunningAdvice;

import java.lang.reflect.Method;

/**
 * Created by xiaoyiyiyo on 2018/3/13.
 */
public class StartTimeBeforeMethod implements BeforeRunningAdvice {

    public void before(Method method, Object[] args, Object target) {
        long startTime = System.currentTimeMillis();
        System.out.println("start time...");
    }
}
