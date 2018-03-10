package com.xiaoyiyiyo.advisor;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class TargetSource {

    private Class<?> targetCLass;

    private Object targetObject;

    public TargetSource() {

    }

    public TargetSource(Class<?> targetCLass, Object targetObject) {
        this.targetCLass = targetCLass;
        this.targetObject = targetObject;
    }

    public Class<?> getTargetCLass() {
        return targetCLass;
    }

    public void setTargetCLass(Class<?> targetCLass) {
        this.targetCLass = targetCLass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }
}
