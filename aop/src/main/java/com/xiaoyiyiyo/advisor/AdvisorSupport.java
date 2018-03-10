package com.xiaoyiyiyo.advisor;

import com.xiaoyiyiyo.interceptor.AopMethodInterceptor;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class AdvisorSupport extends Advisor {

    private TargetSource targetSource;

    private List<AopMethodInterceptor> list = new LinkedList<AopMethodInterceptor>();

    public AdvisorSupport() {

    }

    public AdvisorSupport(TargetSource targetSource, List<AopMethodInterceptor> list) {
        this.targetSource = targetSource;
        this.list = list;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public List<AopMethodInterceptor> getList() {
        return list;
    }

    public void setList(List<AopMethodInterceptor> list) {
        this.list = list;
    }

    public void addAopMethodInterceptor(AopMethodInterceptor interceptor) {
        list.add(interceptor);
    }

    public void addAopMethodInterceptor(List<AopMethodInterceptor> aopMethodInterceptors) {
        list.addAll(aopMethodInterceptors);
    }
}
