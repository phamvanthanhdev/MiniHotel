package com.minihotel.models;

public class CartItem {
    private Integer idHangPhong;
    private String tenHangPhong;
    private String hinhAnh;
    private Integer giaPhong;
    private Integer soLuong;
    private Integer soLuongTrong;

    public CartItem(Integer idHangPhong, String tenHangPhong, String hinhAnh, Integer giaPhong, Integer soLuong, Integer soLuongTrong) {
        this.idHangPhong = idHangPhong;
        this.tenHangPhong = tenHangPhong;
        this.hinhAnh = hinhAnh;
        this.giaPhong = giaPhong;
        this.soLuong = soLuong;
        this.soLuongTrong = soLuongTrong;
    }

    public Integer getIdHangPhong() {
        return idHangPhong;
    }

    public void setIdHangPhong(Integer idHangPhong) {
        this.idHangPhong = idHangPhong;
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

    public Integer getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(Integer giaPhong) {
        this.giaPhong = giaPhong;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getSoLuongTrong() {
        return soLuongTrong;
    }

    public void setSoLuongTrong(Integer soLuongTrong) {
        this.soLuongTrong = soLuongTrong;
    }
}
