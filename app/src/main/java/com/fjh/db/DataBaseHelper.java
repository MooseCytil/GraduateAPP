//package com.fjh.db;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created by fjh.
// */
//public class DataBaseHelper extends SQLiteOpenHelper{
//
//    // Database Name
//    private static final String DB_NAME = "myhotel.db"; //数据库名称
//    private static final int VERSION = 1;//版本号
//
//    // Table Names
//    public static final String TABLE_USER = "t_user";
//    public static final String TABLE_ORDER = "t_order";
//    public static final String TABLE_ROOM = "t_room";
//    public static final String TABLE_HOTEL = "t_hotel";
//    public static final String TABLE_PICTURE = "t_picture";
//
//    // User Table Columns
//    public static final String KEY_USER_ID = "user_id";
//    public static final String KEY_USER_NAME = "user_name";
//    public static final String KEY_USER_EMAIL = "user_email";
//    public static final String KEY_USER_PWD = "user_pwd";
//    public static final String KEY_USER_PHONE = "user_phone";
//
//    // Hotel Table Columns
//    public static final String KEY_HOTEL_ID = "hotel_id";
//    public static final String KEY_HOTEL_NAME = "hotel_name";
//    public static final String KEY_HOTEL_PHONE = "hotel_phone";
//    public static final String KEY_HOTEL_ADDRESS = "hotel_address";
//    public static final String KEY_HOTEL_CONFIG = "hotel_config";
//    public static final String KEY_HOTEL_PROVINCE = "hotel_province";
//
//    // Room Table Columns
//    public static final String KEY_ROOM_ID = "room_id";
//    public static final String KEY_ROOM_NAME = "room_name";
//    public static final String KEY_ROOM_INFO = "room_info";
//
//    // Order Table Columns
//    public static final String KEY_ORDER_ID = "order_id";
//    public static final String KEY_ORDER_DATE = "order_date";
//    public static final String KEY_ORDER_INFO = "order_info";
//
//    // Picture Table Columns
//    public static final String KEY_PICTURE_ID = "picture_id";
//    public static final String KEY_PICTURE_NAME = "picture_name";
//    public static final String KEY_PICTURE_PATH = "picture_path";
//
//
//    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    public DataBaseHelper(Context context) {
//        super(context, DB_NAME, null, VERSION);
//    }
//
//    public DataBaseHelper(Context context, String name, int version){
//        this(context,name,null,version);
//    }
//
//
//    //打开或创建数据库,实际上是第一次得到SQLiteDatabase对象的时候才会调用这个方法
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_USER_TABLE = "create table" + TABLE_USER +
//                "(" +
//                KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
//                KEY_USER_NAME + " TEXT," +
//                KEY_USER_EMAIL + " TEXT," +
//                KEY_USER_PWD + " TEXT," +
//                KEY_USER_PHONE + " TEXT," +
//                ")";
//
//        String CREATE_HOTEL_TABLE = "create table" + TABLE_HOTEL +
//                "(" +
//                KEY_HOTEL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                KEY_HOTEL_NAME + " TEXT," +
//                KEY_HOTEL_PHONE + " TEXT," +
//                KEY_HOTEL_ADDRESS + " TEXT," +
//                KEY_HOTEL_CONFIG + " TEXT," +
//                KEY_HOTEL_PROVINCE + " TEXT," +
//                ")";
//
//        String CREATE_ORDER_TABLE = "create table" + TABLE_ORDER +
//                "(" +
//                KEY_ORDER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
//                KEY_ORDER_DATE + " DATETIME," +
//                KEY_ORDER_INFO + " TEXT," +
//                ")";
//
//        String CREATE_ROOM_TABLE = "create table" + TABLE_ROOM +
//                "(" +
//                KEY_ROOM_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
//                KEY_ROOM_NAME + " TEXT," +
//                KEY_ROOM_INFO + " TEXT," +
//                ")";
//
//        String CREATE_PICTURE_TABLE = "create table" + TABLE_PICTURE +
//                "(" +
//                KEY_PICTURE_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
//                KEY_PICTURE_NAME + " TEXT," +
//                KEY_PICTURE_PATH + " TEXT," +
//                ")";
//
//        System.out.println("create tables");
//        db.execSQL(CREATE_USER_TABLE);
//        db.execSQL(CREATE_HOTEL_TABLE);
//        db.execSQL(CREATE_ORDER_TABLE);
//        db.execSQL(CREATE_ROOM_TABLE);
//        db.execSQL(CREATE_PICTURE_TABLE);
//    }
//
//    //版本更新时调用
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion != newVersion) {
//            // Simplest implementation is to drop all old tables and recreate them
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOTEL);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURE);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
//            onCreate(db);
//        }
//    }