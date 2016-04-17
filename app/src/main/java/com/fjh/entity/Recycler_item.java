package com.fjh.entity;

/**
 * Created by fjh on 2016/4/12.
 */
public class Recycler_item {

    private String text;
    private String picName;

    public Recycler_item(String picName, String text) {
        this.picName = picName;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicName() {
        return picName;
    }



    public void setPicName(String picName) {
        this.picName = picName;

    }


}
