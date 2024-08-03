package com.minihotel.managers;

import com.minihotel.api.ApiService;
import com.minihotel.managers.interfaces.IAllThongTinHangPhong;
import com.minihotel.managers.interfaces.IThongTinHangPhongDetails;
import com.minihotel.models.ThongTinHangPhong;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class CallThongTinHangPhongDetails {
    public static void getThongTinHangPhongById(int id,String ngayDenDat,
                                                String ngayDiDat, IThongTinHangPhongDetails callback){
        ApiService.apiService.getThongTinHangPhongById(id, ngayDenDat, ngayDiDat).enqueue(new Callback<ThongTinHangPhong>() {
            @Override
            public void onResponse(Call<ThongTinHangPhong> call, Response<ThongTinHangPhong> response) {
                if(response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onError(new Throwable("Error code: " + response.code()));
            }

            @Override
            public void onFailure(Call<ThongTinHangPhong> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
}
