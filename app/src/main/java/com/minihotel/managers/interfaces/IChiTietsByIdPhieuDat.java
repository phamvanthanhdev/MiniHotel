package com.minihotel.managers.interfaces;

import com.minihotel.models.ChiTietPhieuDat;
import com.minihotel.models.ThongTinHangPhong;

import java.util.List;

public interface IChiTietsByIdPhieuDat {
    void onSuccess(List<ChiTietPhieuDat> chiTietPhieuDats);
    void onError(Throwable t);
}
