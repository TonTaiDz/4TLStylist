package com.example.a4tlstylist.adapter;

import com.example.a4tlstylist.models.NguoiDung;

public class AccountSingle {
    private static AccountSingle instance;
    private NguoiDung nguoiDung = new NguoiDung();

    public static AccountSingle getInstance() {
        if (instance == null){
            instance = new AccountSingle();
        }
        return instance;
    }

    public static void setInstance(AccountSingle instance) {
        AccountSingle.instance = instance;
    }

    public NguoiDung getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDung nguoiDung) {
        this.nguoiDung = nguoiDung;
    }
}
