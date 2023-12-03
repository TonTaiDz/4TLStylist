package com.example.a4tlstylist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, "4TL", null,5 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tNguoiDung = "CREATE TABLE NGUOIDUNG(" +
                "idnguoidung integer primary key autoincrement," +
                " tenkh text," +
                " diachi text," +
                " sdt text ," +
                " tendangnhap text," +
                " matKhau text," +
                " role integer)";
        sqLiteDatabase.execSQL(tNguoiDung);
        sqLiteDatabase.execSQL("INSERT INTO NGUOIDUNG VALUES" +
                "(1,'Tấn Tài','Q12','0789789789','tantai','123',1)," +
                "(2,'Thế Dinh','Q12','0123123123','thevinh','456',1),"+
                "(3,'Thanh Thiện','Tân Bình','0123123123','thanhthien','789',1),"+
                "(4,'Chí Hiếu','Gò Vấp','0456456456','chihieu','789',2)");
        String tLichDat = "CREATE TABLE LICHDAT(idlichdat text primary key ," +
                "thoigian text," +
                "ngay text,"+
                "trangthai text," +
                "idnguoidung integer references NGUOIDUNG(idnguoidung))";
        sqLiteDatabase.execSQL(tLichDat);
        sqLiteDatabase.execSQL("INSERT INTO LICHDAT VALUES(1,'12h30p','1/12/2023','Đã xong',1)," +
                "(2,'12h50p','1/12/2023','Chưa Nhận',2),"+
                "(3,'1h30p','1/12/2023','Chưa Nhận',3)");


        String tDichVu = "CREATE TABLE DICHVU( iddichvu INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tendichvu TEXT," +
                "gia INTEGER)";
        sqLiteDatabase.execSQL(tDichVu);
        sqLiteDatabase.execSQL("INSERT INTO DICHVU VALUES(1,'Cắt tóc',30000)," +
                "(2,'Massage mặt + gội đầu',30000),"+
                "(3,'Nhuộm  tóc',120000)");


        String LichDatCT = "create table LICHDATCT(idlichchitiet integer primary key AUTOINCREMENT," +
                "idlichdat integer references LICHDAT(idlichdat) ," +
                " iddichvu integer references DICHVU( iddichvu)," +
                "giatien integer)";
        sqLiteDatabase.execSQL(LichDatCT);
        sqLiteDatabase.execSQL("INSERT INTO LICHDATCT VALUES (1,2,3,20000),(2,2,2,20000)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LICHDAT");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DICHVU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LICHDATCT;");
            onCreate(sqLiteDatabase);
        }
    }
}
