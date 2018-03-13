package com.xiaoyiyiyo.aop;

import com.xiaoyiyiyo.aop.advisor.AfterRunningAdvice;

import java.lang.reflect.Method;

/**
 * Created by xiaoyiyiyo on 2018/3/13.
 */
public class EndTimeAfterMethod implements AfterRunningAdvice {

    public Object after(Object returnValue, Method method, Object[] args, Object target) {
        long endTime = System.currentTimeMillis();
        System.out.println("end time.");
        return returnValue;
    }
}
