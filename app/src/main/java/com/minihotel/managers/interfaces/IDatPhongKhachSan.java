package com.minihotel.managers.interfaces;

import com.minihotel.dto.ResultResponse;
import com.minihotel.models.ThongTinHangPhong;

public interface IDatPhongKhachSan {
    void onSuccess(ResultResponse response);
    void onError(Throwable t);
}
