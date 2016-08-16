package com.fjh.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by fjh.
 */
public class Hotel extends BmobObject {

    private String hotel_name;
    private String hotel_phone;
    private String hotel_address;
    private String hotel_config;
    private String city_name;
    private String hotel_price;

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotel_config() {
        return hotel_config;
    }

    public void setHotel_config(String hotel_config) {
        this.hotel_config = hotel_config;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getHotel_price() {
        return hotel_price;
    }

    public void setHotel_price(String hotel_price) {
        this.hotel_price = hotel_price;
    }
}
