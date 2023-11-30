package com.example.a4tlstylist.models;

public class LichDatCT {
    private int idLichDatChiTiet;
    private int idLichDat ;
    private int idDichVu;
    private int giaTien;

    public LichDatCT(int idLichDatChiTiet , int idLichDat, int idDichVu, int giaTien) {
        this.idLichDat = idLichDat;
        this.idDichVu = idDichVu;
        this.giaTien = giaTien;

    }

    public LichDatCT() {
    }

    public int getIdLichDat() {
        return idLichDat;
    }

    public void setIdLichDat(int idLichDat) {
        this.idLichDat = idLichDat;
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
