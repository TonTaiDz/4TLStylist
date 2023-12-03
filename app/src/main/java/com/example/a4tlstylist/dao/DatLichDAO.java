package com.example.a4tlstylist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a4tlstylist.database.DbHelper;
import com.example.a4tlstylist.models.DatLich;
import com.example.a4tlstylist.models.HoaDonCT;
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
                list.add(new DatLich(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public ArrayList<HoaDonCT>getHd(){
        ArrayList<HoaDonCT> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT NGUOIDUNG.tenkh AS tenKH, LICHDAT.idlichdat AS idlichdat, LICHDAT.thoigian AS thoigian,LICHDAT.ngay AS ngay, SUM(LICHDATCT.giatien)  AS giaTien,LICHDAT.trangthai AS trangthai \n" +
                "               FROM LICHDATCT  \n" +
                "               JOIN LICHDAT ON LICHDATCT.idlichdat = LICHDAT.idlichdat \n" +
                "               JOIN DICHVU ON LICHDATCT.iddichvu = DICHVU.iddichvu  \n" +
                "               JOIN NGUOIDUNG ON LICHDAT.idnguoidung = NGUOIDUNG.idnguoidung\n" +
                "               GROUP BY(LICHDATCT.idlichdat)", null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDonCT(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public int getTT30(){
        int tt = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(LICHDATCT.giatien)  AS TT\n" +
                "               FROM LICHDATCT  \n" +
                "               JOIN LICHDAT ON LICHDATCT.idlichdat = LICHDAT.idlichdat \n" +
                "               JOIN DICHVU ON LICHDATCT.iddichvu = DICHVU.iddichvu  \n" +
                "               JOIN NGUOIDUNG ON LICHDAT.idnguoidung = NGUOIDUNG.idnguoidung\n" +
                "               WHERE LICHDAT.trangthai = 'Đã xong'", null);
        if (cursor.moveToFirst()){
            tt = cursor.getInt(0);
        }
        return tt;
    }

    public int getTT(String ngay){
        String str[] = new String[1];
        str[0] = ngay;
        int tt = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(LICHDATCT.giatien)  AS TT\n" +
                "               FROM LICHDATCT  \n" +
                "               JOIN LICHDAT ON LICHDATCT.idlichdat = LICHDAT.idlichdat \n" +
                "               JOIN DICHVU ON LICHDATCT.iddichvu = DICHVU.iddichvu  \n" +
                "               JOIN NGUOIDUNG ON LICHDAT.idnguoidung = NGUOIDUNG.idnguoidung\n" +
                "               WHERE LICHDAT.trangthai = 'Đã xong' AND LICHDAT.ngay = ?", str);
        if (cursor.moveToFirst()){
            tt = cursor.getInt(0);
        }
        return tt;
    }


    public int getLuotKhach(){
        int tt =0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(LICHDAT.idlichdat) FROM LICHDAT",null);
        if (cursor.moveToFirst()){
            tt = cursor.getInt(0);
        }
        return tt;
    }


    public ArrayList<HoaDonCT>getHd(int idNguoidung){
        ArrayList<HoaDonCT> list = new ArrayList<>();
        String str[] = new String[1];
        str[0] = idNguoidung+"";
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT NGUOIDUNG.tenkh AS tenKH, LICHDAT.idlichdat AS idlichdat, LICHDAT.thoigian AS thoigian,LICHDAT.ngay AS ngay, SUM(LICHDATCT.giatien)  AS giaTien,LICHDAT.trangthai AS trangthai \n" +
                "               FROM LICHDATCT  \n" +
                "               JOIN LICHDAT ON LICHDATCT.idlichdat = LICHDAT.idlichdat \n" +
                "               JOIN DICHVU ON LICHDATCT.iddichvu = DICHVU.iddichvu  \n" +
                "               JOIN NGUOIDUNG ON LICHDAT.idnguoidung = NGUOIDUNG.idnguoidung\n" +
                "               WHERE NGUOIDUNG.idnguoidung = ? \n" +
                "               GROUP BY (LICHDAT.idlichdat)", str);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDonCT(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<String>getTenDV(String idLichDat){
        ArrayList<String> list = new ArrayList<>();
        String str[] = new String[1];
        str[0] = idLichDat;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DICHVU.tendichvu  AS TT\n" +
                "               FROM LICHDATCT  \n" +
                "               JOIN LICHDAT ON LICHDATCT.idlichdat = LICHDAT.idlichdat \n" +
                "               JOIN DICHVU ON LICHDATCT.iddichvu = DICHVU.iddichvu  \n" +
                "               JOIN NGUOIDUNG ON LICHDAT.idnguoidung = NGUOIDUNG.idnguoidung\n" +
                "               WHERE LICHDATCT.idlichdat = ?", str);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                list.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public long insert(DatLich datLich ){
        ContentValues contentValues = new ContentValues();
        contentValues.put("idlichdat", datLich.getIdlichdat() );
        contentValues.put("thoigian",datLich.getThoigian());
        contentValues.put("ngay",datLich.getNgay());
        contentValues.put("trangthai", datLich.getTrangthai());
        contentValues.put("idnguoidung", datLich.getIdnguoidung());
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        return sqLiteDatabase.insert("LICHDAT" , null , contentValues);
    }

    public void upDateStatus(String idlichdat, String trangthai){
        ContentValues values = new ContentValues();
        values.put("trangthai",trangthai);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.update("LICHDAT", values, "idlichdat=?", new String[]{String.valueOf(idlichdat)});
    }

}
