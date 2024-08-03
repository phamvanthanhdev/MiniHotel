package com.minihotel.dto;

import java.time.LocalDate;
import java.util.List;

public class PhieuDatRequest {
    private String ngayBatDau;
    private String ngayTraPhong;
    private String ghiChu;
    private String ngayTao;
    private Long tienTamUng;
    private Integer idKhachHang;
    private List<ChiTietPhieuDatRequest> chiTietRequests;

    public PhieuDatRequest() {
    }

    public PhieuDatRequest(String ngayBatDau, String ngayTraPhong, String ghiChu, String ngayTao, Long tienTamUng, Integer idKhachHang, List<ChiTietPhieuDatRequest> chiTietRequests) {
        this.ngayBatDau = ngayBatDau;
        this.ngayTraPhong = ngayTraPhong;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.tienTamUng = tienTamUng;
        this.idKhachHang = idKhachHang;
        this.chiTietRequests = chiTietRequests;
    }

    @Override
    public String toString() {
        return "PhieuDatRequest{" +
                "ngayBatDau=" + ngayBatDau +
                ", ngayTraPhong=" + ngayTraPhong +
                ", ghiChu='" + ghiChu + '\'' +
                ", ngayTao=" + ngayTao +
                ", tienTamUng=" + tienTamUng +
                ", idKhachHang=" + idKhachHang +
                ", chiTietRequests=" + chiTietRequests +
                '}';
    }



    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayTraPhong() {
        return ngayTraPhong;
    }

    public void setNgayTraPhong(String ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Long getTienTamUng() {
        return tienTamUng;
    }

    public void setTienTamUng(Long tienTamUng) {
        this.tienTamUng = tienTamUng;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public List<ChiTietPhieuDatRequest> getChiTietRequests() {
        return chiTietRequests;
    }

    public void setChiTietRequests(List<ChiTietPhieuDatRequest> chiTietRequests) {
        this.chiTietRequests = chiTietRequests;
    }
}
