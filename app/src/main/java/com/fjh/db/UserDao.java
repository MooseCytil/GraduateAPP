//package com.fjh.db;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.fjh.model.User;
//
///**
// * Created by fjh.
// */
//public class UserDao {
//
//     SQLiteDatabase sqLiteDatabase;
//     DataBaseHelper dataBaseHelper;
//
//    public UserDao(Context context){
//        dataBaseHelper = new DataBaseHelper(context);
//        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
//    }
//
//    /**
//     * 注册时增加用户记录
//     * @param user
//     */
//    public void addUser(User user){
//        try {
//            String sql = "insert into " + dataBaseHelper.TABLE_USER +
//                    "( " +
//                    dataBaseHelper.KEY_USER_NAME + ", " +
//                    dataBaseHelper.KEY_USER_EMAIL + ", " +
//                    dataBaseHelper.KEY_USER_PWD + ", " +
//                    dataBaseHelper.KEY_USER_PHONE + ", " +
//                    ") values (?, ?, ?, ?) ;";
//            sqLiteDatabase.execSQL(sql, new String[]{user.getUserName(), user.getUserEmail(), user.getUserPwd(), user.getUserPhone()});
//        }catch (Exception e){
//            e.getStackTrace();
//        }finally {
//            sqLiteDatabase.close();
//        }
//
//    }
//
//    /**
//     * 按条件查询是否有指定用户，条件为邮箱及密码
//     * @param useremail
//     * @param pwd
//     * @return
//     */
//    public User getUser(String useremail, String pwd) {
//        String sql = "select * from " + dataBaseHelper.TABLE_USER + " where user_email = ? and user_pwd = ? ;" ;
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{useremail, pwd});
//        User user = new User();
//        try {
//            if (cursor.moveToFirst())
//            {
//                user.setUserId(cursor.getInt(cursor.getColumnIndex(dataBaseHelper.KEY_USER_ID)));
//                user.setUserName(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_USER_NAME)));
//                user.setUserEmail(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_USER_EMAIL)));
//                user.setUserPwd(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_USER_PWD)));
//                user.setUserPhone(cursor.getString(cursor.getColumnIndex(dataBaseHelper.KEY_USER_PHONE)));
//            }
//        }catch (Exception e){
//            e.getStackTrace();
//        }
//        return user;
//    }
//
//
//}
