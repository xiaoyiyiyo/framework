package com.xiaoyiyiyo.ioc.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xiaoyiyiyo.ioc.bean.BeanDefinition;
import com.xiaoyiyiyo.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

/**
 * Created by xiaoyiyiyo on 2018/3/8.
 */
public class JsonApplicationContext extends BeanFactoryImpl{
    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<BeanDefinition> beanDefinitions = JsonUtils.readValue(is, new TypeReference<List<BeanDefinition>>(){});

        if(beanDefinitions != null && !beanDefinitions.isEmpty()) {

            for (BeanDefinition beanDefinition : beanDefinitions) {
                registerBean(beanDefinition.getName(), beanDefinition);
            }
        }
    }

}
