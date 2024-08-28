package com.minihotel.managers.interfaces;

import com.minihotel.dto.ResultResponse;
import com.minihotel.models.KhachHang;

public interface IDangKy {
    void onSuccess(ResultResponse response);
    void onError(Throwable t);
}
