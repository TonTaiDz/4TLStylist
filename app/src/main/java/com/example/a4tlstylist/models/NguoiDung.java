package com.example.a4tlstylist.models;

public class NguoiDung {
    private int tennd;
    private String sdt;
    private String diachi;
    private String tendangnhap;
    private String matKhau;
    private int role;

    public NguoiDung(int tennd, String sdt, String diachi, String tendangnhap, String matKhau, int role) {
        this.tennd = tennd;
        this.sdt = sdt;
        this.diachi = diachi;
        this.tendangnhap = tendangnhap;
        this.matKhau = matKhau;
        this.role = role;
    }

    public NguoiDung(String sdt, String diachi, String tendangnhap, String matKhau, String pass) {
        this.sdt = sdt;
        this.diachi = diachi;
        this.tendangnhap = tendangnhap;
        this.matKhau = matKhau;
    }

    public int getTennd() {
        return tennd;
    }

    public void setTennd(int tennd) {
        this.tennd = tennd;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
