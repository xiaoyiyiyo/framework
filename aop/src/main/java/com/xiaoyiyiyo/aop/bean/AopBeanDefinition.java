package com.xiaoyiyiyo.aop.bean;

import com.xiaoyiyiyo.ioc.bean.BeanDefinition;

import java.util.List;

/**
 * Created by xiaoyiyiyo on 2018/3/11.
 */
public class AopBeanDefinition extends BeanDefinition {

    private String target;

    private List<String> adviceNames;

    public AopBeanDefinition() {
    }

    public AopBeanDefinition(String target, List<String> adviceNames) {
        this.target = target;
        this.adviceNames = adviceNames;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<String> getAdviceNames() {
        return adviceNames;
    }

    public void setAdviceNames(List<String> adviceNames) {
        this.adviceNames = adviceNames;
    }
}
