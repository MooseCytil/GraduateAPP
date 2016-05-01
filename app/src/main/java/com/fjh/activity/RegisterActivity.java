package com.fjh.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.fjh.db.DataBaseHelper;

/**
 * Created by fjh
 */
public class RegisterActivity extends AppCompatActivity {

    private AppCompatTextView text_login;
    private AppCompatButton button_register;
    private Toolbar toolbar;
    private AppCompatEditText editText_username;
    private AppCompatEditText editText_pwd;
    private AppCompatEditText editText_email;
    private AppCompatEditText editText_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        text_login = (AppCompatTextView) findViewById(R.id.text_login);
        button_register = (AppCompatButton) findViewById(R.id.button_register);
        editText_email = (AppCompatEditText) findViewById(R.id.edittext_regis_email);
        editText_phone = (AppCompatEditText) findViewById(R.id.edittext_regis_phone);
        editText_pwd = (AppCompatEditText) findViewById(R.id.edittext_regis_pwd);
        editText_username = (AppCompatEditText) findViewById(R.id.edittext_regis_username);

        toolbar.setNavigationIcon(R.drawable.left_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });

        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = editText_phone.getText().toString();
                String email = editText_email.getText().toString();
                String pwd = editText_pwd.getText().toString();
                String username = editText_username.getText().toString();

                ContentValues cv = new ContentValues();
                cv.put("email", email);
                cv.put("password", pwd);

                //创建了一个DatabaseHelper对象，只执行这句话是不会创建或打开连接的
                DataBaseHelper dataBaseHelper = new DataBaseHelper(RegisterActivity.this);
                //只有调用了DatabaseHelper的getWritableDatabase()方法或者getReadableDatabase()方法之后，才会创建或打开一个连接
                SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
                // 调用insert方法，就可以将数据插入到数据库当中
                // 第一个参数:表名称
                // 第二个参数：SQl不允许一个空列，如果ContentValues是空的，那么这一列被明确的指明为NULL值
                // 第三个参数：ContentValues对象
                sqLiteDatabase.insert("user", null, cv);
                System.out.print("email为:" + email + ",密码为:" + pwd);

                Toast.makeText(getApplicationContext(), "注册成功，正在跳转", Toast.LENGTH_SHORT ).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }, 2000);

            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }


}
