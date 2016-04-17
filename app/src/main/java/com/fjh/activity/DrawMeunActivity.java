package com.fjh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.widget.ImageView;

/**
 * Created by fjh on 2016/4/17.
 */
public class DrawMeunActivity extends AppCompatActivity {

    private ImageView image;
    private AppCompatTextView text1;
    private AppCompatTextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        image = (ImageView) findViewById(R.id.image);
        text1 = (AppCompatTextView) findViewById(R.id.id_username);
        text2 = (AppCompatTextView) findViewById(R.id.id_email);


    }




}
