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
        this.IdNgayDung = idNgayDung;
        this.TenKH = tenKH;
        this.DiaChi = diaChi;
        this.Sdt = sdt;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.role = role;
    }
    public NguoiDung( String tenKH, String diaChi, String sdt, String tenDangNhap, String matKhau, int role) {
        this.TenKH = tenKH;
        this.DiaChi = diaChi;
        this.Sdt = sdt;
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
        this.IdNgayDung = idNgayDung;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        this.TenKH = tenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        this.DiaChi = diaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        this.Sdt = sdt;
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
