package com.minihotel.models;

public class ChiTietPhieuDat {
    private Integer soLuong;
    private String tenHangPhong;
    private String hinhAnh;
    private int donGia;

    public ChiTietPhieuDat(Integer soLuong, String tenHangPhong, String hinhAnh, int donGia) {
        this.soLuong = soLuong;
        this.tenHangPhong = tenHangPhong;
        this.hinhAnh = hinhAnh;
        this.donGia = donGia;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenHangPhong() {
        return tenHangPhong;
    }

    public void setTenHangPhong(String tenHangPhong) {
        this.tenHangPhong = tenHangPhong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
