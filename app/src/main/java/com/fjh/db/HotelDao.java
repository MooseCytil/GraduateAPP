//package com.fjh.db;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.fjh.model.Hotel;
//
///**
// * Created by fjh.
// */
//public class HotelDao {
//
//    private SQLiteDatabase sqLiteDatabase;
//    private DataBaseHelper dataBaseHelper;
//
//    public HotelDao(Context context){
//        dataBaseHelper = new DataBaseHelper(context);
//        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
//    }
//
//    /**
//     * 根据城市搜索酒店
//     * @param province
//     * @return
//     */
//    public Hotel getHotelByProvince(String province){
//        String sql = "select * from " + dataBaseHelper.TABLE_HOTEL + " where hotel_province = ?;" ;
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{province});
//        Hotel hotel = new Hotel();
//        try {
//            if (cursor.moveToFirst())
//            {
//                hotel.setHotel_id(cursor.getInt(cursor.getColumnIndex(dataBaseHelper.KEY_HOTEL_ID)));
//                hotel.setHotelName(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_HOTEL_NAME)));
//                hotel.setHotelPhone(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_HOTEL_PHONE)));
//                hotel.setHotelAddress(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_HOTEL_ADDRESS)));
//                hotel.setHotelConfig(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_HOTEL_CONFIG)));
//                hotel.setHotelProvince(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_HOTEL_PROVINCE)));
//            }
//        }catch (Exception e){
//            e.getStackTrace();
//        }finally {
//            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
//            }
//        }
//        return hotel;
//    }
//
//
//
//}
