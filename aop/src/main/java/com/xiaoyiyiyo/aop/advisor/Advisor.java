package com.xiaoyiyiyo.aop.advisor;

/**
 * Created by xiaoyiyiyo on 2018/3/10.
 */
public class Advisor {

    private Advice advice;

    private PointCut pointCut;

    public Advisor(){

    }

    public Advisor(Advice advice, PointCut pointCut) {
        this.advice = advice;
        this.pointCut = pointCut;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public PointCut getPointCut() {
        return pointCut;
    }

    public void setPointCut(PointCut pointCut) {
        this.pointCut = pointCut;
    }
}
