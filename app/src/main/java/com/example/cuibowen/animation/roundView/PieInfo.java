package com.example.cuibowen.animation.roundView;

import android.graphics.Paint;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created by cuibowen on 2017/11/20.
 */

public class PieInfo {
    private String id;
    private float start;
    private float end;
    private Paint mPaint;
    private Paint mCopyPaint;
    private String percentageText;

    public String getPercentageText() {
        return percentageText;
    }

    public void setPercentageText(String percentageText) {
        this.percentageText = percentageText;
    }

    public PieInfo() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public Paint getmPaint() {
        return mPaint;
    }
    public Paint getCopyPaint() {
        mCopyPaint.set(mPaint);
        return mCopyPaint;
    }
    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
        if (mCopyPaint == null) mCopyPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mCopyPaint.set(mPaint);
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public float getEnd() {
        return end;
    }

    public void setEnd(float end) {
        this.end = end;
    }

    public boolean isInAngleRange(float angle) {
        return angle >= start && angle <= end;
    }

    public float getSweep() {
        return Math.abs(end - start);
    }

    public boolean equalsWith(PieInfo info) {
        if (info == null) return false;
        return TextUtils.equals(info.getId(), id);
    }
    public float getMiddleSweep(){
        return start + getSweep() / 2;
    }

}
