package com.xiaoyiyiyo.ioc.utils;

/**
 * Created by xiaoyiyiyo on 2018/3/7.
 */
public class ClassUtils {
    public static ClassLoader getDefultClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class loadClass(String className) {
        try {
            return getDefultClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
