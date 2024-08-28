package com.minihotel.managers;

import com.minihotel.api.ApiService;
import com.minihotel.managers.interfaces.ITimKiemThongTinHangPhong;
import com.minihotel.managers.interfaces.ITimKiemThongTinHangPhongTheoGia;
import com.minihotel.models.ThongTinHangPhong;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallTimKiemThongTinHangPhongTheoGia {
    public static void timKiemThongTinHangPhongTheoGia(String ngayDenDat, String ngayDiDat, long giaMin, long giaMax, ITimKiemThongTinHangPhongTheoGia callback){
        ApiService.apiService.timKiemThongTinHangPhongTheoTheoGia(ngayDenDat, ngayDiDat, giaMin, giaMax).enqueue(new Callback<List<ThongTinHangPhong>>() {
            @Override
            public void onResponse(Call<List<ThongTinHangPhong>> call, Response<List<ThongTinHangPhong>> response) {
                if(response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onError(new Throwable("Error code: " + response.code()));
            }

            @Override
            public void onFailure(Call<List<ThongTinHangPhong>> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
}
