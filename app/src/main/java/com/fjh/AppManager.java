package com.fjh;

import android.app.Application;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * Author by fjh.
 */
public class AppManager extends Application {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
