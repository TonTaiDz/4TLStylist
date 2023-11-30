package com.example.a4tlstylist.models;

public class NguoiDung {


    private int IdNgayDung ;
   private String TenKH ;
   private String DiaChi;
   private String Sdt;
   private String tenDangNhap;
   private String matKhau ;
   private int role ;
    public NguoiDung(int idNgayDung, String tenKH, String diaChi, String sdt, String tenDangNhap, String matKhau, int role) {
        IdNgayDung = idNgayDung;
        TenKH = tenKH;
        DiaChi = diaChi;
        Sdt = sdt;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.role = role;
    }
    public NguoiDung( String tenKH, String diaChi, String sdt, String tenDangNhap, String matKhau, int role) {

        TenKH = tenKH;
        DiaChi = diaChi;
        Sdt = sdt;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.role = role;
    }

    public NguoiDung() {
    }

    public int getIdNgayDung() {
        return IdNgayDung;
    }

    public void setIdNgayDung(int idNgayDung) {
        IdNgayDung = idNgayDung;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
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
