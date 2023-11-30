package com.example.a4tlstylist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a4tlstylist.database.DbHelper;
import com.example.a4tlstylist.models.DatLich;
import com.example.a4tlstylist.models.NguoiDung;

import java.util.ArrayList;

public class DatLichDAO {
    private DbHelper dbHelper;

    public DatLichDAO(Context context){ dbHelper = new DbHelper(context);}

    //lay ds datlich
    public ArrayList<DatLich>getdsDatLich(){
        ArrayList<DatLich> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LICHDAT", null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new DatLich(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public long insert(DatLich datLich ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("idlichdat", datLich.getIdlichdat() );
        contentValues.put("thoigian",datLich.getThoigian());
        contentValues.put("trangthai", datLich.getTrangthai());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.insert("LICHDAT", null,contentValues);
        return sqLiteDatabase.insert("LICHDAT" , null , contentValues);
    }

}
