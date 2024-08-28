package com.minihotel.managers;

import com.minihotel.api.ApiService;
import com.minihotel.dto.DangKyRequest;
import com.minihotel.dto.ResultResponse;
import com.minihotel.managers.interfaces.IDangKy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallDangKy {
    public static void dangKyTaiKhoan(DangKyRequest dangKyRequest, IDangKy callback){
        ApiService.apiService.dangKyTaiKhoan(dangKyRequest).enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                if(response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onError(new Throwable("Error code: " + response.code()));
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
}
