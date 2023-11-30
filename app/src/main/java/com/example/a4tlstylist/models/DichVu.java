package com.example.a4tlstylist.models;

public class DichVu {
    private int IdDichVu;
    private String tenDichVu;
    private int giaDichVu;

    public DichVu(int idDichVu, String tenDichVu, int giaDichVu) {
        IdDichVu = idDichVu;
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
    }

    public DichVu() {
    }

    public int getIdDichVu() {
        return IdDichVu;
    }

    public void setIdDichVu(int idDichVu) {
        IdDichVu = idDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public int getGiaDichVu() {
        return giaDichVu;
    }

    public void setGiaDichVu(int giaDichVu) {
        this.giaDichVu = giaDichVu;
    }


}
