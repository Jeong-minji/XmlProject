package com.seoul_weather.seoulweather;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Jeong Minji on 2018-01-12.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "NanumBarunpenR.ttf"))
                .addBold(Typekit.createFromAsset(this, "NanumBarunpenB.ttf"));

    }
}
