package com.zhuyunjian.manhua;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.androidannotations.annotations.EApplication;

/**
 * Created by dell on 2016/3/14.
 */
@EApplication
public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Fresco.initialize(this);
    }

    public static App getInstance(){return app;}

    public static App getApp() {
        return app;
    }

    public static void setApp(App app) {
        App.app = app;
    }
}
