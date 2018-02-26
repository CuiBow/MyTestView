package com.example.cuibowen.animation;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cuibowen.animation.R;
import com.example.cuibowen.animation.scoll.MyScrollView;

public class ScollActivity extends AppCompatActivity implements MyScrollView.OnScrollListener {
    private TextView search_edit;
    private MyScrollView myScrollView;
    private int searchLayoutTop;

    LinearLayout search01,search02;
    RelativeLayout rlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        //初始化控件
        init();
    }

    private void init() {
        search_edit = (TextView)findViewById(R.id.search_edit);
        myScrollView = (MyScrollView)findViewById(R.id.myScrollView);
        search01 = (LinearLayout)findViewById(R.id.search01);
        search02 = (LinearLayout)findViewById(R.id.search02);
        rlayout = (RelativeLayout)findViewById(R.id.rlayout);
        myScrollView.setOnScrollListener(this);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            searchLayoutTop = rlayout.getBottom();//获取searchLayout的顶部位置
        }
    }

    //监听滚动Y值变化，通过addView和removeView来实现悬停效果
    @Override
    public void onScroll(int scrollY) {
        if(scrollY >= searchLayoutTop){
            if (search_edit.getParent()!=search01) {
                search02.removeView(search_edit);
                search01.addView(search_edit);
            }
        }else{
            if (search_edit.getParent()!=search02) {
                search01.removeView(search_edit);
                search02.addView(search_edit);
            }
        }
    }
}
