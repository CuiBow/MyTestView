package com.example.cuibowen.animation.homeView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.cuibowen.animation.R;

/**
 * Created by cuibowen on 2017/11/26.
 */

public class PhoneView extends LinearLayout implements ScanView.OnTimeEndListener {
    private ScanView scanView;
    private ImageView topImage;
    private ImageView bottomImage;
    private int height;
    private Context context;
    private RelativeLayout topBottomRel;


    public PhoneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        View view= LayoutInflater.from(context).inflate(R.layout.phone_item,null);

        addView(view);

        measure(0,0);

        scanView= view.findViewById(R.id.scan_view);
        topImage=view.findViewById(R.id.top_image);
        bottomImage=view.findViewById(R.id.bottom_image);
        topBottomRel=view.findViewById(R.id.top_bottom_rel);
        scanView.setListener(this);

        height=topBottomRel.getMeasuredHeight()/2;

        init();
    }
    private void init(){
        //此方法为裁剪老手机bitmap经常内存泄漏
        ClipDrawable top= (ClipDrawable) topImage.getDrawable();
        ClipDrawable button= (ClipDrawable) bottomImage.getDrawable();

        top.setLevel(5000);
        button.setLevel(5000);
    }

    public void startAnimation(){
        if (topImage!=null&&bottomImage!=null){
            ObjectAnimator animator = ObjectAnimator.ofFloat(topImage, "translationY", 0, -height);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(bottomImage, "translationY", 0, height);
            AnimatorSet animSet = new AnimatorSet();
            animSet.play(animator).with(animator1);
            animSet.setDuration(300);
            animSet.setInterpolator(new LinearInterpolator());
            animSet.start();

            animSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    topImage.setVisibility(GONE);
                    bottomImage.setVisibility(GONE);
                    scanView.setScanLine(true);
                    scanView.timeStart();
                }
            });
        }
    }


    public void reStart(){
        topImage.setVisibility(VISIBLE);
        bottomImage.setVisibility(VISIBLE);
    }

    @Override
    public void onTimeEndListener() {
        //scanView.setScanLine(false);
        //Toast.makeText(context,"扫描结束",Toast.LENGTH_LONG).show();
    }
    public void recycle(){
        if (scanView!=null){
            scanView.recycle();
        }
    }
}
