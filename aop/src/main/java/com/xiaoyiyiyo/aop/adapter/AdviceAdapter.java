package com.xiaoyiyiyo.aop.adapter;

import com.xiaoyiyiyo.aop.advisor.Advisor;
import com.xiaoyiyiyo.aop.interceptor.AopMethodInterceptor;

/**
 * Created by xiaoyiyiyo on 2018/3/13.
 */
public interface AdviceAdapter {

    AopMethodInterceptor getInterceptor(Advisor advisor);
}
