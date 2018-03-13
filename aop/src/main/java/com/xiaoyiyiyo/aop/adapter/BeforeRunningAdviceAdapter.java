package com.xiaoyiyiyo.aop.adapter;

import com.xiaoyiyiyo.aop.advisor.Advisor;
import com.xiaoyiyiyo.aop.advisor.BeforeRunningAdvice;
import com.xiaoyiyiyo.aop.interceptor.AopMethodInterceptor;
import com.xiaoyiyiyo.aop.interceptor.BeforeRunningAdviceInterceptor;

/**
 * Created by xiaoyiyiyo on 2018/3/13.
 */
public class BeforeRunningAdviceAdapter implements AdviceAdapter{

    private BeforeRunningAdviceAdapter() {
    }

    private static final BeforeRunningAdviceAdapter INSTANCE = new BeforeRunningAdviceAdapter();

    public static BeforeRunningAdviceAdapter getInstance() {
        return INSTANCE;
    }

    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        BeforeRunningAdvice beforeRunningAdvice = (BeforeRunningAdvice) advisor.getAdvice();
        return new BeforeRunningAdviceInterceptor(beforeRunningAdvice);
    }
}
