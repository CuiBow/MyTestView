package com.example.cuibowen.animation.homeView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cuibowen.animation.R;

public class Main7Activity extends AppCompatActivity  {

    private HomePhoneView homePhoneView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        homePhoneView= (HomePhoneView) findViewById(R.id.home_page_view);
        homePhoneView.startAnimation();

        homePhoneView.setListener(new HomePhoneView.OnButtonClickListener() {
            @Override
            public void onButtonClickListener() {
                Toast.makeText(Main7Activity.this,"点击了",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (homePhoneView!=null){
            homePhoneView.recycle();
        }

    }
}
