package com.fjh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * drawerlayout--即将开始的行程activity
 * Created by fjh.
 */
public class DrawerReservedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatTextView textView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView_title = (AppCompatTextView) findViewById(R.id.text_title);

        toolbar.setNavigationIcon(R.drawable.left_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });

        textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrawerReservedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
