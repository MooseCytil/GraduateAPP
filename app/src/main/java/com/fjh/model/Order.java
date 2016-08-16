package com.fjh.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by fjh.
 */
public class Order extends BmobObject {

    private String hotel_name;
    private String user_name;

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
