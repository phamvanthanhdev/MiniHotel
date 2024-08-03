package com.minihotel.managers.interfaces;

import com.minihotel.models.ThongTinHangPhong;

import java.util.List;

public interface IHangPhongsTheoThoiGian {
    void onSuccess(List<ThongTinHangPhong> thongTinHangPhongs);
    void onError(Throwable t);
}
