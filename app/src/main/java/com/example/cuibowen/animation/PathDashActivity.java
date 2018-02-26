package com.example.cuibowen.animation;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class PathDashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_dash);


        Observable.just("ss").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                if (s.equals("1231"))
                {
                    return s;
                }else{
                    return "123";
                }
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("eee",s);
            }
        });


    }




    // 开始计时

}
