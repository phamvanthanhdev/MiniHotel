package com.minihotel.managers;

import com.minihotel.api.ApiService;
import com.minihotel.managers.interfaces.IAllThongTinHangPhong;
import com.minihotel.managers.interfaces.IHangPhongsTheoThoiGian;
import com.minihotel.models.ThongTinHangPhong;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallHangPhongTheoThoiGian {
    public static void getHangPhongsTheoThoiGian(String ngayDenDat, String ngayDiDat,IHangPhongsTheoThoiGian callback){
        ApiService.apiService.getHangPhongsTheoThoiGian(ngayDenDat, ngayDiDat).enqueue(new Callback<List<ThongTinHangPhong>>() {
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
