package com.stylight.codingtask.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;


public class BaseApplication extends Application  {

    private static Context context;

    public static Context getContext() {
        return context;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        context = getApplicationContext();
    }


}
