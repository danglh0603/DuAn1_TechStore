package com.DuAn1.techstore.Model;

public class GioHang {
    private int maSanPham;
    private int soLuongMua;

    public GioHang(int maSanPham, int soLuongMua) {
        this.maSanPham = maSanPham;
        this.soLuongMua = soLuongMua;
    }

    public GioHang() {
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
}
