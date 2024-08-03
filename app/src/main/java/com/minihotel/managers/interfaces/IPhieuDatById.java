package com.minihotel.managers.interfaces;

import com.minihotel.models.PhieuDat;
import com.minihotel.models.ThongTinHangPhong;

public interface IPhieuDatById {
    void onSuccess(PhieuDat phieuDat);
    void onError(Throwable t);
}
