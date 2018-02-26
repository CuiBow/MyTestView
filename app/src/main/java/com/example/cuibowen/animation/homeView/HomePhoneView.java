package com.example.cuibowen.animation.homeView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cuibowen.animation.R;

/**
 * Created by cuibowen on 2017/12/1.
 */

public class HomePhoneView extends LinearLayout implements View.OnClickListener {

    private PhoneView phoneView;
    private Button button;
    private int height;
    private ImageView yao1;
    private Button shouzhi;
    private Handler handler=new Handler();
    private OnButtonClickListener listener;

    public void setListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    public HomePhoneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view= LayoutInflater.from(context).inflate(R.layout.home_page_view,null);
        addView(view);

        phoneView=view.findViewById(R.id.phone_view);
        shouzhi= view.findViewById(R.id.shouzhi);
        button=view.findViewById(R.id.button);
        yao1= view.findViewById(R.id.yao1);

        button.setOnClickListener(this);

        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();

    }
    public void startAnimation(){
        try {
            AnimationDrawable  animationDrawable = (AnimationDrawable) yao1.getDrawable();
            animationDrawable.start();
        }catch (Exception e){
            System.out.print("Animation Exception");
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                phoneTranslate();
            }
        },500);

    }

    private void phoneTranslate(){
        viewTranslateY(phoneView,0,0,height,phoneView.getY(),400,new LinearInterpolator(),"button");
    }

    private void viewTranslateY(View view, float fromX, float toX, float fromY, float toY, int duration, Interpolator interpolator, final String type){
        view.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(fromX, toX,fromY, toY);
        translateAnimation.setDuration(duration);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if (type.equals("button")){
                    buttonAnimation();
                }else{
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button.setBackground(getResources().getDrawable(R.drawable.anniuan));
                            phoneView.startAnimation();
                        }
                    }, 500);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            shouzhi.setVisibility(View.GONE);
                            button.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        }
                    }, 700);

                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(translateAnimation);
    }

    private void buttonAnimation(){
        button.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "translationY",height,0);
        ObjectAnimator animator1=ObjectAnimator.ofFloat(button,"scaleX",0.5f,1f);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(button,"scaleY",0.5f,1f);
        animator.setInterpolator(new LinearInterpolator());
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator).with(animator1).with(animator2);
        animSet.setDuration(400);
        animSet.start();

        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                shouzhi.setVisibility(View.VISIBLE);
                viewTranslateY(shouzhi,0,0,height+200,0,600,new LinearInterpolator(),"finger");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                if (listener!=null){
                    listener.onButtonClickListener();
                }
                break;
        }
    }
    public interface OnButtonClickListener{
        void onButtonClickListener();
    }
    public void recycle(){
        if (phoneView!=null){
            phoneView.recycle();
        }
    }
}
