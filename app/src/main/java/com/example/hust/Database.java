package com.example.hust;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //truy vấn không trả về kết quả
    public void QuerryData(String sql)
    {
        SQLiteDatabase database =getWritableDatabase();
        database.execSQL(sql);
    }
    //truy vấn trả về kết quả
    public Cursor getData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    //insert môn học
    public void INSERT_STUDY(String mssv, String hoten)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Subject1 VALUES(null, ?, ?)";
        SQLiteStatement statement =database.compileStatement(sql);
        statement.clearBindings();
        //lấy các giá trị tại các vị trí
        statement.bindString(1, mssv);
        statement.bindString(2, hoten);


        statement.executeInsert();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
