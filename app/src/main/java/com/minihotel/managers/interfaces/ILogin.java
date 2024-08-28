package com.minihotel.managers.interfaces;

import com.minihotel.models.KhachHang;

public interface ILogin {
    void onSuccess(KhachHang khachHang);
    void onError(Throwable t);
}
