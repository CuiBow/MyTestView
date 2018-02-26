package com.example.cuibowen.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cuibowen.animation.BezierView.WaveBezierView;

public class WaveActivity extends AppCompatActivity {
    private WaveBezierView waveBezierView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        waveBezierView= (WaveBezierView) findViewById(R.id.WaveBezierView);

    }

    public void click(View view){
        waveBezierView.start();
    }
    public void click2(View view){
        waveBezierView.stop();
    }
}
