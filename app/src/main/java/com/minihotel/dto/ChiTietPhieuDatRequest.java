package com.minihotel.dto;

public class ChiTietPhieuDatRequest {
    private Integer idHangPhong;
    private Integer soLuong;

    public ChiTietPhieuDatRequest(Integer idHangPhong, Integer soLuong) {
        this.idHangPhong = idHangPhong;
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuDatRequest{" +
                "idHangPhong=" + idHangPhong +
                ", soLuong=" + soLuong +
                '}';
    }

    public Integer getIdHangPhong() {
        return idHangPhong;
    }

    public void setIdHangPhong(Integer idHangPhong) {
        this.idHangPhong = idHangPhong;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
}
