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


        String tNguoiDunng = "CREATE TABLE NGUOIDUNG(" +
                "idnguoidung integer primary key autoincrement," +
                " ten text," +
                " diachi text," +
                " sdt text ," +
                " email text," +
                " matKhau text," +
                " role integer)";
        sqLiteDatabase.execSQL(tNguoiDunng);
            // data mau nguoi dung
            sqLiteDatabase.execSQL("INSERT INTO NGUOIDUNG VALUES(1,'Tấn Tài','Q12','0789789789','tai@gmail.com','123',1)," +
                                                                "(2,'Thế Dinh','Q12','0123123123','vinh@gmail.com','456',1),"+
                                                                "(3,'Thanh Thiện','Tân Bình','0123123123','thien@gmail.com','789',1),"+
                                                                "(4,'Chí Hiếu','Gò Vấp','0456456456','hieu@gmail.com','789',1)");



        String tLichDat = "CREATE TABLE LICHDAT(idlichdat integer primary key autoincrement," +
                "thoigian text," +
                "trangthai text," +
                "idnguoidung integer references NGUOIDUNG(idnguoidung))";
        sqLiteDatabase.execSQL(tLichDat);
        // data mau lich dat
        sqLiteDatabase.execSQL("INSERT INTO LICHDAT VALUES(1,'12h30p','Đã xong',1)," +
                                                          "(2,'12h50p','Chưa Nhận',2),"+
                                                          "(3,'1h30p','Chưa Nhận',3)");




        String tDichVu = "CREATE TABLE DICHVU( iddichvu INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tendichvu TEXT," +
                "gia INTEGER)";
        sqLiteDatabase.execSQL(tDichVu);
        //data mau DichVU
        sqLiteDatabase.execSQL("INSERT INTO DICHVU VALUES(1,'Cắt tóc',30000)," +
                                                        "(2,'Massage mặt + gội đầu',30000),"+
                                                        "(3,'Nhuộm  tóc',120000)");

        String tCTLichDat = "CREATE TABLE CTLICHDAT(idlichdat INTEGER REFERENCES LICHDAT(idlicdat)," +
                "iddichvu INTEGER REFERENCES DICHVU(iddichvu)," +
                "giatien INTEGER," +
                "ghichu TEXT," +
                "PRIMARY KEY (idlichdat, iddichvu))";
        sqLiteDatabase.execSQL(tCTLichDat);
        //data mau CTLichDat
        sqLiteDatabase.execSQL("INSERT INTO CTLICHDAT VALUES(1,1,30)," +
                                                      "(3,1,30)," +
                                                      "(2,3,120)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LICHDAT");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DICHVU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CTLICHDAT");
            onCreate(sqLiteDatabase);
        }
    }
}
