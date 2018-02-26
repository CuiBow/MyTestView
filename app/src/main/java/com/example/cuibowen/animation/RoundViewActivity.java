package com.example.cuibowen.animation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.example.cuibowen.animation.roundView.ArcConfig;
import com.example.cuibowen.animation.roundView.PixelUtils;
import com.example.cuibowen.animation.roundView.RoundArcView;

import java.util.ArrayList;
import java.util.List;

public class RoundViewActivity extends AppCompatActivity {
    private RoundArcView roundView;
    private RadioGroup radio;
    private boolean isFull=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        PixelUtils.init(this);
        roundView= (RoundArcView) findViewById(R.id.round_view);
        radio= (RadioGroup) findViewById(R.id.radio);


        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id= radioGroup.getCheckedRadioButtonId();
                switch (id){
                    case R.id.empty:
                        isFull=false;
                        break;
                    case R.id.full:
                        isFull=true;
                        break;
                    default:
                        isFull=false;
                        break;
                }
                ArcConfig.getInstance().setFullOrStroke(isFull);
                roundView.startAnimation();
            }
        });

        new ArcConfig.Builder()
                .setPadding(60)
                .setIsFullOrStroke(isFull)
                .setDuration(1000)
                .setNumItem(20,getColor("FF446767"))
                .setNumItem(30,getColor("FFFFD28C"))
                .setNumItem(25,getColor("FFbb76b4"))
                .setNumItem(40,getColor("FFFFD28C"))
                .setNumItem(35,getColor("c53e4d"))
                .Build();
        roundView.setConfig(ArcConfig.getInstance());
    }

    private int getColor(String colorStr) {
        if (TextUtils.isEmpty(colorStr)) return Color.BLACK;
        if (!colorStr.startsWith("#")) colorStr = "#" + colorStr;
        return Color.parseColor(colorStr);
    }
}
