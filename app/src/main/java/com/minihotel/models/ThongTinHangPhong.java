package com.minihotel.models;

public class ThongTinHangPhong {
    private Integer idHangPhong;
    private String tenHangPhong;
    private String moTa;
    private String hinhAnh;
    private String tenKieuPhong;
    private String tenLoaiPhong;
    private Float phanTramGiam;
    private Integer soNguoiToiDa;
    private Integer giaGoc;
    private Integer giaKhuyenMai;
    private Integer soLuongTrong;

    public ThongTinHangPhong(Integer idHangPhong, String tenHangPhong, String moTa, String hinhAnh, String tenKieuPhong, String tenLoaiPhong, Float phanTramGiam, Integer soNguoiToiDa, Integer giaGoc, Integer giaKhuyenMai) {
        this.idHangPhong = idHangPhong;
        this.tenHangPhong = tenHangPhong;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.tenKieuPhong = tenKieuPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.phanTramGiam = phanTramGiam;
        this.soNguoiToiDa = soNguoiToiDa;
        this.giaGoc = giaGoc;
        this.giaKhuyenMai = giaKhuyenMai;
    }

    public ThongTinHangPhong(Integer idHangPhong, String tenHangPhong, String moTa, String hinhAnh, String tenKieuPhong, String tenLoaiPhong, Float phanTramGiam, Integer soNguoiToiDa, Integer giaGoc, Integer giaKhuyenMai, Integer soLuongTrong) {
        this.idHangPhong = idHangPhong;
        this.tenHangPhong = tenHangPhong;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.tenKieuPhong = tenKieuPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.phanTramGiam = phanTramGiam;
        this.soNguoiToiDa = soNguoiToiDa;
        this.giaGoc = giaGoc;
        this.giaKhuyenMai = giaKhuyenMai;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenKieuPhong() {
        return tenKieuPhong;
    }

    public void setTenKieuPhong(String tenKieuPhong) {
        this.tenKieuPhong = tenKieuPhong;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public Float getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(Float phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Integer getSoNguoiToiDa() {
        return soNguoiToiDa;
    }

    public void setSoNguoiToiDa(Integer soNguoiToiDa) {
        this.soNguoiToiDa = soNguoiToiDa;
    }

    public Integer getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(Integer giaGoc) {
        this.giaGoc = giaGoc;
    }

    public Integer getGiaKhuyenMai() {
        return giaKhuyenMai;
    }

    public void setGiaKhuyenMai(Integer giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }

    public Integer getSoLuongTrong() {
        return soLuongTrong;
    }

    public void setSoLuongTrong(Integer soLuongTrong) {
        this.soLuongTrong = soLuongTrong;
    }
}
