package com.anji.appsp.sdktest;

import android.app.Application;

import com.anji.appsp.sdk.AppSpConfig;

public class AppContext extends Application {
    private static AppContext mInstance;
    public static final String appKey = "24b14615101b4fe0ab9595d6e1d5e428";

    public static AppContext getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppSpConfig.getInstance().init(this, appKey);
        mInstance = this;
    }

}
