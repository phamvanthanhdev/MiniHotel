package com.minihotel.managers;

import com.minihotel.api.ApiService;
import com.minihotel.managers.interfaces.IGetKhachHangById;
import com.minihotel.models.KhachHang;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallKhachHangById {
    public static void getKhachHangById(int id, IGetKhachHangById callback){
        ApiService.apiService.getKhachHangTheoId(id).enqueue(new Callback<KhachHang>() {
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
