package com.minihotel.managers.interfaces;

import com.minihotel.models.PhieuDat;
import com.minihotel.models.ThongTinHangPhong;

import java.util.List;

public interface IPhieuDatsByIdKhachHang {
    void onSuccess(List<PhieuDat> phieuDats);
    void onError(Throwable t);
}
