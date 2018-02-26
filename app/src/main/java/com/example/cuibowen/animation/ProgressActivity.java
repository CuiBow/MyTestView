package com.example.cuibowen.animation;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cuibowen.animation.BezierView.ProgressView;

public class ProgressActivity extends AppCompatActivity {
    ProgressView progressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        progressView= (ProgressView) findViewById(R.id.progress);


    }
    public void progress(View view){

        progressView.setPrgoressInt(90);
    }

}
