package com.fjh;

import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * Author by fjh on 2016/4/16.
 */
public class AppManager {

    private static Stack<AppCompatActivity> activitiesStack;

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public AppCompatActivity currentActivity(){
        AppCompatActivity compatActivity = activitiesStack.lastElement();
        return compatActivity;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(AppCompatActivity activity){
        if(activitiesStack==null){
            activitiesStack=new Stack<AppCompatActivity>();
        }
        activitiesStack.add(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(AppCompatActivity activity){
        if (activity != null)
        {
            activitiesStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishCurrentActivity(){
        AppCompatActivity compatActivity = activitiesStack.lastElement();
        finishActivity(compatActivity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        for (int i = 0; i < activitiesStack.size(); i++){
            if (null != activitiesStack.get(i)){
                activitiesStack.get(i).finish();
            }
        }
        activitiesStack.clear();
    }


}
