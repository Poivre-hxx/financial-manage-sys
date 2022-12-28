package com.example.financial;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DBHelper extends SQLiteOpenHelper {
    // 数据库版本号
    private static final int DATABASE_VERSION = 1;

    // 数据库名
    private static final String DATABASE_NAME = "financial.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表
        String CREATE_TABLE_EXPEND = "CREATE TABLE " + Expend.TABLE + "("
                + Expend.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Expend.KEY_expName + " VARCHAR(255), "
                + Expend.KEY_label + " VARCHAR(255), "
                + Expend.KEY_price + " FLOAT, "
                + Expend.KEY_month + " INTEGER, "
                + Expend.KEY_date + " INTEGER )";
        db.execSQL(CREATE_TABLE_EXPEND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS " + Expend.TABLE);
        // 再次创建表
        onCreate(db);
    }

}
