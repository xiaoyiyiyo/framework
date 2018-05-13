package com.xiaoyiyiyo.ioc.core;

import com.xiaoyiyiyo.ioc.bean.BeanDefinition;
import com.xiaoyiyiyo.ioc.bean.ConstructorArg;
import com.xiaoyiyiyo.ioc.bean.PropertyArg;
import com.xiaoyiyiyo.ioc.utils.BeanUtils;
import com.xiaoyiyiyo.ioc.utils.ClassUtils;
import com.xiaoyiyiyo.ioc.utils.ReflectionUtils;
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
            //自动注入了
//            injectBean(bean);

            beanMap.put(name, bean);
        }
        return bean;
    }

    public Object createBean(BeanDefinition beanDefinition) throws Exception {

        Object bean = null;

        String beanName = beanDefinition.getClassName();
        Class clz = ClassUtils.loadClass(beanName);
        if (clz == null) {
            throw new Exception("can not find bean by beanName: " + beanName);
        }
        List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
        List<Class> argClazzs = new ArrayList<Class>();
        if (constructorArgs != null && !constructorArgs.isEmpty()) {
            List<Object> objects = new ArrayList<Object>();
            for (ConstructorArg constructorArg : constructorArgs) {
                if (constructorArg != null ) {
                    if (constructorArg.getValue() != null) {
                        objects.add(constructorArg.getValue());
                    } else {
                        String ref = constructorArg.getRef();
                        argClazzs.add(ClassUtils.loadClass(beanDefineMap.get(ref).getClassName()));
                        objects.add(getBean(ref));
                    }
                } else {
                    throw new Exception("An constructor param can't be null.");
                }
            }

            Class[] constructorArgTypes = argClazzs.toArray(new Class[]{});
            bean = BeanUtils.instanceByReflect(clz, constructorArgTypes, objects.toArray());
        } else {
            bean = BeanUtils.instanceByReflect(clz, null, null);
        }

        //set注入
        injectField(bean, beanDefinition);
        return bean;
    }

    private void injectField(Object bean, BeanDefinition beanDefinition) throws Exception {

        List<PropertyArg> properties = beanDefinition.getPropertyArgs();

        // 注意是 super class
        Field[] fields = bean.getClass().getSuperclass().getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : fields) {
            fieldMap.put(field.getName(), field);
        }

        if (properties != null) {
            for (PropertyArg property : properties) {
                String name = property.getName();
                if (fieldMap.containsKey(name)) {
                    String value = property.getValue();
                    String ref = property.getRef();
                    if (!StringUtils.isEmpty(value) && !StringUtils.isEmpty(ref)) {
                        throw new Exception("Property is only allowed to contain either 'ref' attribute OR 'value' attribute.");
                    }

                    name = StringUtils.uncapitalize(name);
                    if (!StringUtils.isEmpty(value)) {
                      ReflectionUtils.injectField(fieldMap.get(name), bean, value);
                    }

                    if (!StringUtils.isEmpty(ref)) {
                        if (beanNameSet.contains(name)) {
                            Object fieldBean = getBean(ref);
                            if (fieldBean != null) {
                                ReflectionUtils.injectField(fieldMap.get(name),bean,fieldBean);
                            }
                        }
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
