package net.levinh.exercise11;

import android.app.Application;
import android.content.Context;

/**
 * Created by levinh on 05/08/2016.
 */
public class MainApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


}
