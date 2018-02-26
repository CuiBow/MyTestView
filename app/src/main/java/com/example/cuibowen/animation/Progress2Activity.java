package com.example.cuibowen.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cuibowen.animation.homeView.ProgressView;

public class Progress2Activity extends AppCompatActivity {
    private ProgressView progressView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress2);
        progressView= (ProgressView) findViewById(R.id.progress);

    }
    public void progressStart(View view){
        progressView.setSchedule(90);
    }
}
