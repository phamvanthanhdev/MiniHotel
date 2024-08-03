package com.minihotel.dto;

public class KhachHangRequest {
    private String cmnd;
    private String hoTen;
    private String sdt;
    private String diaChi;
    private String maSoThue;
    private String email;
    private Integer idTaiKhoan;

    public KhachHangRequest(String cmnd, String hoTen, String sdt, String diaChi, String maSoThue, String email) {
        this.cmnd = cmnd;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.maSoThue = maSoThue;
        this.email = email;
    }

    public KhachHangRequest() {
    }

    public KhachHangRequest(String cmnd, String hoTen, String sdt, String diaChi, String maSoThue, String email, Integer idTaiKhoan) {
        this.cmnd = cmnd;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.maSoThue = maSoThue;
        this.email = email;
        this.idTaiKhoan = idTaiKhoan;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(Integer idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    @Override
    public String toString() {
        return "KhachHangRequest{" +
                "cmnd='" + cmnd + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", sdt='" + sdt + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", maSoThue='" + maSoThue + '\'' +
                ", email='" + email + '\'' +
                ", idTaiKhoan=" + idTaiKhoan +
                '}';
    }
}
