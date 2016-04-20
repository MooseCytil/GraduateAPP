//package com.fjh.activity;
//
//import android.app.DatePickerDialog;
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.AppCompatEditText;
//import android.view.View;
//import android.widget.DatePicker;
//import android.widget.SearchView;
//
//import java.util.Calendar;
//
///**
// * Created by fjh on 2016/4/19.
// */
//public class DateTimeActivity extends AppCompatActivity{
//
//    private SearchView search;
//    private AppCompatEditText editTextCheckIn;
//    private AppCompatEditText editTextCheckOut;
//    private Calendar calendar; //通过Calendar获取系统时间
//    private DatePickerDialog datePickerDialog;
//    private int year, month, day;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //锁定屏幕
//        setContentView(R.layout.main_booking_layout);
////        DatePicker datePicker2 = (DatePicker) findViewById(R.id.date_pick2);
//        search = (SearchView) findViewById(R.id.main_search);
//        editTextCheckIn = (AppCompatEditText) findViewById(R.id.edit_checkin);
//        editTextCheckOut = (AppCompatEditText) findViewById(R.id.edit_checkout);
//
//        // 获取日历对象
//        calendar = Calendar.getInstance();
//
//        editTextCheckIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //初始化DatePickerDialog
//                new DatePickerDialog(DateTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        editTextCheckIn.setText(year + "年" + monthOfYear + "月" + dayOfMonth+ "日");
//                    }
//                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//
//        editTextCheckOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //初始化DatePickerDialog
//                new DatePickerDialog(DateTimeActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        editTextCheckIn.setText(year + "年" + monthOfYear + "月" + dayOfMonth+ "日");
//                    }
//                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//
//    }
//
//
//}
