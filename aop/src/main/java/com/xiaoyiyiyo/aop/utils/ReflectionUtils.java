package com.xiaoyiyiyo.aop.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class ReflectionUtils {

    public static Object invokeMethodUseReflection(Object target, Method method, Object[] args) {
        method.setAccessible(true);
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
