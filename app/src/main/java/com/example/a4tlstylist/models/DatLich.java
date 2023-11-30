package com.example.a4tlstylist.models;

public class DatLich {
    private String idlichdat;
    private String thoigian;
    private String trangthai;
    private int idnguoidung;

    public DatLich(String idlichdat, String thoigian, String trangthai, int idnguoidung) {
        this.idlichdat = idlichdat;
        this.thoigian = thoigian;
        this.trangthai = trangthai;
        this.idnguoidung = idnguoidung;
    }

    public DatLich() {
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
