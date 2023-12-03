package com.example.a4tlstylist.models;

public class LichDatCT {
    private int idLichDatChiTiet;
    private String idlichdat;
    private int idDichVu;
    private int giaTien;



    public LichDatCT() {
    }

    public LichDatCT(int idLichDatChiTiet, String idlichdat, int idDichVu, int giaTien) {
        this.idLichDatChiTiet = idLichDatChiTiet;
        this.idlichdat = idlichdat;
        this.idDichVu = idDichVu;
        this.giaTien = giaTien;
    }

    public LichDatCT(String idlichdat, int idDichVu, int giaTien) {
        this.idlichdat = idlichdat;
        this.idDichVu = idDichVu;
        this.giaTien = giaTien;
    }

    public int getIdLichDatChiTiet() {
        return idLichDatChiTiet;
    }

    public void setIdLichDatChiTiet(int idLichDatChiTiet) {
        this.idLichDatChiTiet = idLichDatChiTiet;
    }

    public String getIdlichdat() {
        return idlichdat;
    }

    public void setIdlichdat(String idlichdat) {
        this.idlichdat = idlichdat;
    }

    public int getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        this.idDichVu = idDichVu;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
