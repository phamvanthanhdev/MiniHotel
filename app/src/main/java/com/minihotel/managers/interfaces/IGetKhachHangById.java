package com.minihotel.managers.interfaces;

import com.minihotel.models.ChiTietPhieuDat;
import com.minihotel.models.KhachHang;

import java.util.List;

public interface IGetKhachHangById {
    void onSuccess(KhachHang khachHang);
    void onError(Throwable t);
}
