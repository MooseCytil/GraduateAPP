package com.fjh.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fjh on 2016/5/1.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "myhotel.db"; //数据库名称
    private final static int VERSION = 1;//版本号

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public DataBaseHelper(Context context, String name, int version){
        this(context,name,null,version);
    }


    //打开或创建数据库,实际上是第一次得到SQLiteDatabase对象的时候才会调用这个方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =  "create table user(email varchar(60) not null , password varchar(60) not null);";
        System.out.println("create a database");
        db.execSQL(sql);
    }

    //版本更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
