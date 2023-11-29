package com.example.a4tlstylist.models;

public class DatLich {
    private int idlichdat;
    private String thoigian;
    private String trangthai;
    private int idnguoidung;

    public DatLich(int idlichdat, String thoigian, String trangthai, int idnguoidung) {
        this.idlichdat = idlichdat;
        this.thoigian = thoigian;
        this.trangthai = trangthai;
        this.idnguoidung = idnguoidung;
    }

    public int getIdlichdat() {
        return idlichdat;
    }

    public void setIdlichdat(int idlichdat) {
        this.idlichdat = idlichdat;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getIdnguoidung() {
        return idnguoidung;
    }

    public void setIdnguoidung(int idnguoidung) {
        this.idnguoidung = idnguoidung;
    }
}
