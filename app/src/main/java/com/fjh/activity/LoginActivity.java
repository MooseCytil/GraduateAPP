package com.fjh.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.fjh.db.DataBaseHelper;

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

        text_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
//                loginIntent.putExtra("username", editText_username.getText().toString());
//                LoginActivity.this.setResult(user_result_code, loginIntent);
//                LoginActivity.this.finish();
                String username = editText_username.getText().toString();
                String userpwd = editText_pwd.getText().toString();
                boolean flag = login(username, userpwd);
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userpwd))
                {
                    Toast.makeText(LoginActivity.this, "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(flag)
                    {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                        loginIntent.putExtra("username", editText_username.getText().toString());
                        LoginActivity.this.setResult(user_result_code, loginIntent);
                        LoginActivity.this.finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "请先注册", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }

                }

            }
        });

    }

    //隐藏输入法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    public boolean login(String username, String userpwd){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        String sql="select * from user where email=? and password=?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{username,userpwd});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

//    public void loginByPost(String username, String userpwd){
//        URL url = null;
//        try{
//            url = new URL(baseURL);
//            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//            urlConnection.setRequestMethod("POST");
//            urlConnection.setDoOutput(true); // 发送POST请求必须设置允许输出
//            urlConnection.setDoInput(true); // 发送POST请求必须设置允许输入
//            urlConnection.setRequestProperty("contentType", "application/x-www-form-urlencoded");
//            String data = "username=" + URLEncoder.encode(username, "UTF-8")
//                    + "&userpass=" + URLEncoder.encode(userpwd, "UTF-8");
//            //获取输出流
//            OutputStream os = urlConnection.getOutputStream();
//            os.write(data.getBytes());
//            os.flush();
//
//        }catch (MalformedURLException e){
//            e.getStackTrace();
//        }catch (IOException e){
//            e.getStackTrace();
//        }
//    }

}
