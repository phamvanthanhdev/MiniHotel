package com.minihotel.managers.interfaces;

import com.minihotel.models.ThongTinHangPhong;

import java.util.List;

public interface IThongTinHangPhongDetails {
    void onSuccess(ThongTinHangPhong thongTinHangPhong);
    void onError(Throwable t);
}
