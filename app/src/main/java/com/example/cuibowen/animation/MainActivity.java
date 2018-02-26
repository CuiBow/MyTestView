package com.example.cuibowen.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void secondBezier(View view)
    {
        startActivity(new Intent(MainActivity.this,BezierSecondActivity.class));
    }

    public void thirdBezier(View view)
    {
        startActivity(new Intent(MainActivity.this,BezierThirdActivity.class));
    }

    public void pathBezier(View view)
    {
        startActivity(new Intent(MainActivity.this,BezierPathActivity.class));
    }

    public void waveBezier(View view)
    {
        startActivity(new Intent(MainActivity.this,WaveBezierActivity.class));
    }

    public void shopBezier(View view)
    {
        startActivity(new Intent(MainActivity.this,ShopPathActivity.class));
    }

    public void pathDash(View view)
    {
        startActivity(new Intent(MainActivity.this,PathDashActivity.class));
    }

    public void pathMeasure(View view)
    {
        startActivity(new Intent(MainActivity.this,PathMeasureActivity.class));
    }

    public void pathPos(View view)
    {
        startActivity(new Intent(MainActivity.this,PathPosActivity.class));
    }
    public void countDownView(View view)
    {
        startActivity(new Intent(MainActivity.this,CountDownActivity.class));
    }
    public void wave(View view)
    {
        startActivity(new Intent(MainActivity.this,WaveActivity.class));
    }
    public void progress(View view)
    {
        startActivity(new Intent(MainActivity.this,ProgressActivity.class));
    }
    public void progress2(View view)
    {
        startActivity(new Intent(MainActivity.this,Progress2Activity.class));
    }
    public void round(View view)
    {
        startActivity(new Intent(MainActivity.this,RoundViewActivity.class));
    }
    public void scoll(View view)
    {
        startActivity(new Intent(MainActivity.this,ScollActivity.class));
    }
}
