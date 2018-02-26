package com.example.cuibowen.animation.roundView;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cuibowen on 2017/11/20.
 */

public class ArcConfig  {
    private static ArcConfig instance;
    private List<PieInfo> pieInfos;
    private int padding;
    private boolean isFullOrStroke;
    private long duration;


    public long getDuration() {
        return duration;
    }

    public int getPadding() {
        return padding;
    }


    public boolean isFullOrStroke() {
        return isFullOrStroke;
    }

    public static ArcConfig getInstance(){
        return instance;
    }

    public void setFullOrStroke(boolean fullOrStroke) {
        isFullOrStroke = fullOrStroke;
        if (pieInfos!=null){
            for (PieInfo data : pieInfos) {
                Paint paint=data.getmPaint();
                if (!isFullOrStroke){
                    paint.setStyle(Paint.Style.STROKE);
                }else{
                    paint.setStyle(Paint.Style.FILL);
                }
            }
        }
    }

    public ArcConfig(Builder builder){
        this.pieInfos=builder.pieInfos;
        this.padding=builder.padding;
        this.isFullOrStroke=builder.isFullOrStroke;
        this.duration=builder.duration;
    }

    public static class Builder{
        public List<Float> moneyList=new ArrayList<>();
        public List<PieInfo> pieInfos=new ArrayList<>();
        public List<Paint> paints=new ArrayList<>();
        public int padding=20;
        public boolean isFullOrStroke=false;
        public long duration=1000;

        public boolean isShowText=true;

        public Builder setPadding(int padding){
            this.padding=padding;
            return this;
        }
        public Builder setIsFullOrStroke(boolean isFullOrStroke){
            this.isFullOrStroke=isFullOrStroke;
            return this;
        }
        public Builder setDuration(long duration){
            this.duration=duration;
            return this;
        }
        public Builder isShowText(boolean isShowText){
            this.isShowText=isShowText;
            return this;
        }

        public Builder setNumItem(float money,int color){
            moneyList.add(money);
            Paint mArcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
            mArcPaint.setAntiAlias(true);
            mArcPaint.setColor(color);
            mArcPaint.setStrokeWidth(padding);
            if (!isFullOrStroke){
                mArcPaint.setStyle(Paint.Style.STROKE);
            }else{
                mArcPaint.setStyle(Paint.Style.FILL);
            }
            paints.add(mArcPaint);
            return this;
        }


        public void Build(){
            float sum=0;
            for (int i=0;i<moneyList.size();i++){
                sum+=moneyList.get(i);
            }
            float start=-90;
            for (int i=0;i<moneyList.size();i++){
                PieInfo pieInfo=new PieInfo();
                pieInfo.setStart(start);
                float angle = (float) (360.0 * (moneyList.get(i) / sum));
                angle = Math.max(1.0f, angle);
                pieInfo.setPercentageText(angle+"");
                float endAngle = start + angle;
                pieInfo.setEnd(endAngle);
                start = endAngle;
                pieInfo.setmPaint(paints.get(i));

                pieInfos.add(pieInfo);
            }
            instance=new ArcConfig(this);
        }
    }
    public PieInfo findPieinfoWithAngle(float angle) {
        if (pieInfos!=null){
            for (PieInfo data : pieInfos) {
                if (data.isInAngleRange(angle)) return data;
            }
        }

        return null;
    }
}
