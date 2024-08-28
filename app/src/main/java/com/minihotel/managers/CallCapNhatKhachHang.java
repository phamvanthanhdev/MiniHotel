package com.minihotel.managers;


import com.minihotel.api.ApiService;
import com.minihotel.dto.KhachHangRequest;
import com.minihotel.managers.interfaces.ICapNhatKhachHang;
import com.minihotel.models.KhachHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallCapNhatKhachHang {
    public static void capNhatKhachHang(int id, KhachHangRequest request, ICapNhatKhachHang callback){
        ApiService.apiService.updateKhachHang(id, request).enqueue(new Callback<KhachHang>() {
            @Override
            public void onResponse(Call<KhachHang> call, Response<KhachHang> response) {
                if(response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onError(new Throwable("Error code: " + response.code()));
            }

            @Override
            public void onFailure(Call<KhachHang> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
}
