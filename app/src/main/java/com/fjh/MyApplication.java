package com.fjh;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by fjh on 2016/4/30.
 */
public class MyApplication extends Application {

    public static RequestQueue mQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mQueue = Volley.newRequestQueue(getApplicationContext());//使用全局上下文
    }

    public static RequestQueue getmQueue(){
        return mQueue;
    }

}
