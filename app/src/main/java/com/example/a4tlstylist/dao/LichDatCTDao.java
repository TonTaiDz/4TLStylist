package com.example.a4tlstylist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a4tlstylist.database.DbHelper;
import com.example.a4tlstylist.models.DatLich;
import com.example.a4tlstylist.models.LichDatCT;

import java.util.ArrayList;

public class LichDatCTDao {
    private DbHelper dbHelper;

    public LichDatCTDao(Context context){ dbHelper = new DbHelper(context);}

    //lay ds datlich
    public ArrayList<LichDatCT> getdsDatLichCT(){
        ArrayList<LichDatCT> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LICHDATCT", null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new LichDatCT(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public long insert(LichDatCT lichDatCT ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("idlichdat", lichDatCT.getIdLichDat() );
        contentValues.put("iddichvu",lichDatCT.getIdDichVu());
        contentValues.put("giatien", lichDatCT.getGiaTien());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        return sqLiteDatabase.insert("LICHDATCT" , null , contentValues);
    }

    //SELECT NGUOIDUNG.tenkh, LICHDAT.idlichdat, LICHDAT.thoigian, DICHVU.tendichvu, LICHDATCT.giatien
    //               FROM LICHDATCT
    //               JOIN LICHDAT ON LICHDATCT.idlichdat = LICHDAT.idlichdat
    //               JOIN DICHVU ON LICHDATCT.iddichvu = DICHVU.iddichvu
    //               JOIN NGUOIDUNG ON LICHDAT.idnguoidung = NGUOIDUNG.idnguoidung
}
