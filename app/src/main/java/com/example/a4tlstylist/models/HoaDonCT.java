package com.example.a4tlstylist.models;

import java.io.Serializable;

public class HoaDonCT implements Serializable {
    private String TenKH ;
    private String idlichdat;
    private String thoigian;
    private String ngay;
    private int giaTien;

    private String trangthai;


    public HoaDonCT(String tenKH, String idlichdat, String thoigian, String ngay, int giaTien, String trangthai) {
        this.TenKH = tenKH;
        this.idlichdat = idlichdat;
        this.thoigian = thoigian;
        this.ngay = ngay;
        this.giaTien = giaTien;
        this.trangthai = trangthai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {this.TenKH = tenKH;
    }

    public String getIdlichdat() {
        return idlichdat;
    }

    public void setIdlichdat(String idlichdat) {
        this.idlichdat = idlichdat;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
