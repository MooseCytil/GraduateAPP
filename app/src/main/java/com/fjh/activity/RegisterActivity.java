package com.fjh.activity;

import android.content.Intent;
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

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

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

        editText_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (editText_email.getText().toString().trim().length() < 6) {
                        Toast.makeText(getApplicationContext(), "邮箱地址太短", Toast.LENGTH_SHORT).show();
                    } else if (editText_email.getText().toString().trim().length() == 0) {
                        Toast.makeText(getApplicationContext(), "邮箱不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        editText_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    if (editText_pwd.getText().toString().trim().length() < 6)
                    {
                        Toast.makeText(getApplicationContext(), "密码过短", Toast.LENGTH_SHORT).show();
                    }
                    else if (editText_pwd.getText().toString().trim().length() > 10)
                    {
                        Toast.makeText(getApplicationContext(), "密码过长", Toast.LENGTH_SHORT).show();
                    }
                    else if (editText_pwd.getText().toString().trim().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        editText_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    if (editText_username.getText().toString().trim().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        editText_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    if (editText_phone.getText().toString().trim().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "手机号不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //“立即登录”按钮
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //注册按钮
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(editText_username.getText().toString());
                bmobUser.setPassword(editText_pwd.getText().toString());
                bmobUser.setEmail(editText_email.getText().toString());
                bmobUser.setMobilePhoneNumber(editText_phone.getText().toString());
                bmobUser.signUp(getApplicationContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "注册成功,正在跳转", Toast.LENGTH_LONG).show();
                        //将登录信息保存至首页
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                BmobUser.getCurrentUser(getApplicationContext()); //缓存的user对象
                                Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(registerIntent);
                            }
                        }, 2000);
                    }
                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }


}
