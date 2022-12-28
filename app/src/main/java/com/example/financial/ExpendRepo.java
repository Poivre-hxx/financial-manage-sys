package com.example.financial;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ExpendRepo {
    public DBHelper dbHelper;

    public ExpendRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Expend expend) {
        // 打开连接，写入数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Expend.KEY_expName, expend.expName);
        values.put(Expend.KEY_label, expend.label);
        values.put(Expend.KEY_price, expend.price);
        values.put(Expend.KEY_month, expend.month);
        values.put(Expend.KEY_date, expend.date);

        // 插入表
        long id = db.insert(Expend.TABLE, null, values);
        db.close();
        return (int) id;
    }

    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 删除表
        db.delete(Expend.TABLE, Expend.KEY_ID + "= ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public void update(Expend expend) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Expend.KEY_expName, expend.expName);
        values.put(Expend.KEY_label, expend.label);
        values.put(Expend.KEY_price, expend.price);
        values.put(Expend.KEY_month, expend.month);
        values.put(Expend.KEY_date, expend.date);

        // 更新表
        db.update(Expend.TABLE, values, Expend.KEY_ID + "= ?", new String[] {String.valueOf(expend.id)});
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> getExpendList() {
        // 打开连接，读取数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " + Expend.KEY_ID + "," + Expend.KEY_expName + "," + Expend.KEY_label + "," + Expend.KEY_price + "," + Expend.KEY_month + "," + Expend.KEY_date + " FROM " + Expend.TABLE;
        ArrayList<HashMap<String, String>> expendList = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // 遍历所有行
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> expend = new HashMap<>();
                expend.put("id", cursor.getString(cursor.getColumnIndex(Expend.KEY_ID)));
                expend.put("expName", cursor.getString(cursor.getColumnIndex(Expend.KEY_expName)));
                expend.put("label", cursor.getString(cursor.getColumnIndex(Expend.KEY_label)));
                expend.put("price", cursor.getString(cursor.getColumnIndex(Expend.KEY_price)));
                expend.put("month", cursor.getString(cursor.getColumnIndex(Expend.KEY_month)));
                expend.put("date", cursor.getString(cursor.getColumnIndex(Expend.KEY_date)));
                expendList.add(expend);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return expendList;
    }

    @SuppressLint("Range")
    public Expend getExpendById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " + Expend.KEY_ID + "," + Expend.KEY_expName + "," + Expend.KEY_label + "," + Expend.KEY_price + "," + Expend.KEY_month + "," + Expend.KEY_date + " FROM " + Expend.TABLE + " WHERE " + Expend.KEY_ID + "=?";
        int iCount = 0;
        Expend expend = new Expend();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()) {
            do {
                expend.id = cursor.getInt(cursor.getColumnIndex(Expend.KEY_ID));
                expend.expName = cursor.getString(cursor.getColumnIndex(Expend.KEY_expName));
                expend.label = cursor.getString(cursor.getColumnIndex(Expend.KEY_label));
                expend.price = cursor.getFloat(cursor.getColumnIndex(Expend.KEY_price));
                expend.month = cursor.getInt(cursor.getColumnIndex(Expend.KEY_month));
                expend.date = cursor.getInt(cursor.getColumnIndex(Expend.KEY_date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return expend;
    }


}
