package com.DuAn1.techstore.Model;

public class HoaDon {

    private int maHoaDon;
    private int maKhachHang;

    public HoaDon(int maHoaDon, int maKhachHang) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
    }

    public HoaDon() {
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

}
