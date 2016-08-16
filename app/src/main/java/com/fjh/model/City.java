package com.fjh.model;

import cn.bmob.v3.BmobObject;

/**
 * 城市列表
 * Created by fjh.
 */
public class City extends BmobObject {

    private String city_name;
    private String city_first;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_first() {
        return city_first;
    }

    public void setCity_first(String city_first) {
        this.city_first = city_first;
    }

}
