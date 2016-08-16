package com.fjh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.fjh.AppManager;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

/**
 * 实现用户的注册以及登录
 * Created by fjh
 */
public class LoginActivity extends AppCompatActivity {

    static final int user_result_code = 1;

    private AppCompatTextView text_forgetpwd;
    private AppCompatTextView text_register;
    private AppCompatButton button_login;
    private AppCompatEditText editText_username;
    private AppCompatEditText editText_pwd;
    private Toolbar toolbar;
    private AppManager appManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        button_login = (AppCompatButton) findViewById(R.id.button_login);
        text_forgetpwd = (AppCompatTextView) findViewById(R.id.text_pwd);
        text_register = (AppCompatTextView) findViewById(R.id.text_register);
        editText_username = (AppCompatEditText) findViewById(R.id.edittext_username);
        editText_pwd = (AppCompatEditText) findViewById(R.id.edittext_pwd);

        toolbar.setNavigationIcon(R.drawable.left_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //"忘记密码"按钮
        text_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //"注册用户"按钮
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        //登录按钮
        button_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //登录用户
                BmobUser loginBmobUser = new BmobUser();
                loginBmobUser.setUsername(editText_username.getText().toString());
                loginBmobUser.setPassword(editText_pwd.getText().toString());
                loginBmobUser.login(getApplicationContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "登录成功,正在跳转", Toast.LENGTH_SHORT).show();
                        BmobUser bmobUser = BmobUser.getCurrentUser(getApplicationContext());
                        ((AppManager)getApplication()).setUsername(bmobUser.getUsername());
                        //保存登录信息至另一个页面
                        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                        loginIntent.putExtra("username", bmobUser.getUsername());
                        loginIntent.putExtra("useremail", bmobUser.getEmail());
                        LoginActivity.this.setResult(user_result_code, loginIntent);
                        LoginActivity.this.finish();
                    }
                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(getApplicationContext(), "登录失败：" + s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //隐藏输入法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

}
