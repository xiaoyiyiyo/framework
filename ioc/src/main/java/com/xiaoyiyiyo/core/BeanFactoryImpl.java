package com.xiaoyiyiyo.core;

import com.xiaoyiyiyo.bean.BeanDefinition;
import com.xiaoyiyiyo.bean.ConstructorArg;
import com.xiaoyiyiyo.utils.BeanUtils;
import com.xiaoyiyiyo.utils.ClassUtils;
import com.xiaoyiyiyo.utils.ReflectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiaoyiyiyo on 2018/3/8.
 */
public class BeanFactoryImpl implements BeanFactory {

    private static final ConcurrentHashMap<String, Object> beanMap =
            new ConcurrentHashMap<String, Object>();

    private static final ConcurrentHashMap<String, BeanDefinition> beanDefineMap =
            new ConcurrentHashMap<String, BeanDefinition>();

    private static final Set<String> beanNameSet = Collections.synchronizedSet(new HashSet<String>());

    public Object getBean(String name) throws Exception {
        Object bean = beanMap.get(name);
        if (bean != null) {
            return bean;
        }

        bean = createBean(beanDefineMap.get(name));

        // 注入依赖的属性
        if (bean != null) {
            injectBean(bean);

            beanMap.put(name, bean);
        }
        return bean;
    }

    public Object createBean(BeanDefinition beanDefinition) throws Exception {
        String beanName = beanDefinition.getClassName();
        Class clz = ClassUtils.loadClass(beanName);
        if (clz == null) {
            throw new Exception("can not find bean by beanName: " + beanName);
        }
        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        if (constructorArgs != null && !constructorArgs.isEmpty()) {
            List<Object> objects = new ArrayList<Object>();
            for (ConstructorArg constructorArg : constructorArgs) {
                if (constructorArg != null && constructorArg.getValue() != null) {
                    objects.add(constructorArg.getValue());
                } else {
                    objects.add(getBean(constructorArg.getRef()));
                }
            }

            List<Class> listTmp = new ArrayList<Class>();
            for (Object obj : objects) {
                listTmp.add(obj.getClass());
            }
            Class[] constructorArgTypes = listTmp.toArray(new Class[]{});
            Constructor constructor = clz.getConstructor(constructorArgTypes);
            return BeanUtils.instanceByCglib(clz, constructor, objects.toArray());
        } else {
            return BeanUtils.instanceByCglib(clz, null, null);
        }
    }

    private void injectBean(Object bean) throws Exception {
        // 注意是 super class
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String beanName = field.getName();
                beanName = StringUtils.uncapitalize(beanName);
                if (beanNameSet.contains(field.getName())) {
                    Object fieldBean = getBean(beanName);
                    if (fieldBean != null) {
                        ReflectionUtils.injectField(field,bean,fieldBean);
                    }
                }
            }
        }
    }

    protected void registerBean(String name, BeanDefinition bd){
        beanDefineMap.put(name,bd);
        beanNameSet.add(name);
    }
}
