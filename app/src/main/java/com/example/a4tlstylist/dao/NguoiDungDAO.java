package com.example.a4tlstylist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a4tlstylist.database.DbHelper;
import com.example.a4tlstylist.models.DatLich;
import com.example.a4tlstylist.models.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDAO {
    private final DbHelper dbHelper;

    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);
    }


    public ArrayList<NguoiDung> getDataNguoiDung(){
        ArrayList<NguoiDung> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NguoiDung", null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new NguoiDung(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int login(String tendangnhap, String matKhau, int role) {
        int result = 0;
        String str[] = new String[3];
        str[0] = tendangnhap;
        str[1] = matKhau;
        str[2] = String.valueOf(role);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from NGUOIDUNG where tendangnhap=? and matKhau=? and role=?", str);
        if (c.moveToNext()) {
            result = 1;
        }
        return result;
    }


    public long insert(NguoiDung nguoiDung ){

        ContentValues contentValues = new ContentValues();
        contentValues.put("tenkh", nguoiDung.getTenKH() );
        contentValues.put("sdt",nguoiDung.getSdt());
        contentValues.put("diachi", nguoiDung.getDiaChi());
        contentValues.put("tendangnhap", nguoiDung.getTenDangNhap());
        contentValues.put("matKhau", nguoiDung.getMatKhau() );
        contentValues.put("role", nguoiDung.getRole() );
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.insert("NGUOIDUNG", null,contentValues);
        return sqLiteDatabase.insert("NGUOIDUNG" , null , contentValues);
    }

}
