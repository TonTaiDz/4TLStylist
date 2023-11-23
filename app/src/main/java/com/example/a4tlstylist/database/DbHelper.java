package com.example.a4tlstylist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, "4TL", null,8);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /**
         * role
         * 1 - Người dùng
         * 2 - Thợ cắt tóc
         */


        String tNguoiDunng = "CREATE TABLE NGUOIDUNG(mand integer primary key autoincrement, tennd text, sdt text, diachi text , tendangnhap text, matKhau text, role text)";
            sqLiteDatabase.execSQL(tNguoiDunng);
            // data mau nguoi dung
            sqLiteDatabase.execSQL("INSERT INTO NGUOIDUNG VALUES(1,'Nguyễn Tấn Tài','093485732','Q12 TP.HCM','taint','123',1)," +
                                                                "(2,'Nguyễn Hoài Đức','092374334','Tân Bình','ducnh','456',2),"+
                                                                "(3,'Nguyễn Văn Thịnh','09234544','Đak Song','thinhnv','789',3)");

            String tPhieuMuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, ngaymuon text, ngaytra text, mand integer references NGUOIDUNG(mand))";
            sqLiteDatabase.execSQL(tPhieuMuon);
            // data mau phieu muon
            sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES(1, '20/9/2023', '26/9/2023',1)");

            String tCTPM = "CREATE TABLE CTPM(mactpm integer primary key autoincrement ,mapm integer references PHIEUMUON(mapm), masach integer references SACH(masach), soluong interger)";
            sqLiteDatabase.execSQL(tCTPM);
            //data mau CTPM
            sqLiteDatabase.execSQL("INSERT INTO CTPM VALUES(1,1,1,2),(2,1,2,1)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CTPM");
            onCreate(sqLiteDatabase);
        }
    }
}
