package com.example.cuibowen.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cuibowen.animation.pathView.MyCountDownView;

public class CountDownActivity extends AppCompatActivity implements MyCountDownView.OnCountDownListener {
    private MyCountDownView views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        views= (MyCountDownView) findViewById(R.id.view);

        views.setOnCountDownListener(this);

    }

    public void start(View view){
        views.setTime(5);
    }

    @Override
    public void onCountDownClickListener() {
        Toast.makeText(CountDownActivity.this,"点击了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCountDownSuccessListener() {
        Toast.makeText(CountDownActivity.this,"成功",Toast.LENGTH_SHORT).show();
    }
}
