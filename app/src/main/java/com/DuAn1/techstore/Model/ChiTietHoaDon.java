package com.DuAn1.techstore.Model;

public class ChiTietHoaDon {

    private int maHoaDon;
    private  int maSanPham;
    private int soLuongMua;
    private int tongTien;
    private String ngayMua;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int maHoaDon, int maSanPham, int soLuongMua, int tongTien, String ngayMua) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuongMua = soLuongMua;
        this.tongTien = tongTien;
        this.ngayMua = ngayMua;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

}
