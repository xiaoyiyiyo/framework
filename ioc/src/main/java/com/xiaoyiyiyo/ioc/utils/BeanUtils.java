package com.xiaoyiyiyo.ioc.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * Created by xiaoyiyiyo on 2018/3/7.
 */
public class BeanUtils {

    public static <T> T instanceByCglib(Class<T> clz, Constructor ctr, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(NoOp.INSTANCE);

        if (ctr == null) {
            return (T) enhancer.create();
        } else {
            return (T) enhancer.create(ctr.getParameterTypes(), args);
        }
    }

    public static <T> T instanceByReflect(Class<T> clz, Class[] argTypes, Object[] args) throws Exception {

        if (argTypes == null) {
            return  (T) clz.newInstance();
        }

        Constructor constructor = clz.getConstructor(argTypes);

        return (T) constructor.newInstance(args);
    }
}
