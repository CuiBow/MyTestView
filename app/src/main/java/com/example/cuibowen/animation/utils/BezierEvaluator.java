package com.example.cuibowen.animation.utils;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by cuibowen on 2017/7/24.
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF mFlagPoint;

    public BezierEvaluator(PointF pointF) {
        this.mFlagPoint=pointF;
    }

    @Override
    public PointF evaluate(float v, PointF pointF, PointF t1) {
        return BezierUtil.CalculateBezierPointForQuadratic(v,pointF,mFlagPoint,t1);
    }
}
