package com.xiaoyiyiyo.ioc.utils;

import java.lang.reflect.Field;

/**
 * Created by xiaoyiyiyo on 2018/3/8.
 */
public class ReflectionUtils {

    public static void injectField(Field field, Object obj, Object value) throws IllegalAccessException {
        if (field != null) {
            field.setAccessible(true);
            field.set(obj, value);
        }
    }
}
