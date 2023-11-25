package com.example.a4tlstylist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a4tlstylist.database.DbHelper;
import com.example.a4tlstylist.models.NguoiDung;

public class NguoiDungDAO {
    private DbHelper dbHelper;

    SharedPreferences sharedPreferences;

    public NguoiDungDAO(Context context){
        dbHelper = new DbHelper(context);

        sharedPreferences = context.getSharedPreferences("dataUser", Context.MODE_PRIVATE);
    }

    //kiểm tra thông tin đăng nhập
    public boolean KiemTraDangNhap(String username, String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE tendangnhap = ? AND matkhau = ?", new String[]{username,password});


        //lưu role accout đăng nhập
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("role",cursor.getInt(6));
            editor.apply();
        }

        return cursor.getCount()>0;
    }

    public boolean dangKiTaiKhoan(NguoiDung nguoiDung){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tennd", nguoiDung.getTennd());
        contentValues.put("sdt", nguoiDung.getSdt());
        contentValues.put("diachi", nguoiDung.getDiachi());
        contentValues.put("tendangnhap", nguoiDung.getTendangnhap());
        contentValues.put("matKhau", nguoiDung.getMatKhau());
        contentValues.put("role", 1);

        long check = sqLiteDatabase.insert("NGUOIDUNG", null,contentValues);
        return check != -1;
    }


}
