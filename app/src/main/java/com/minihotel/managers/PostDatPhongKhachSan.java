package com.minihotel.managers;

import com.minihotel.api.ApiService;
import com.minihotel.dto.PhieuDatRequest;
import com.minihotel.dto.ResultResponse;
import com.minihotel.managers.interfaces.IDatPhongKhachSan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDatPhongKhachSan {
    public static void postDatPhongKhachSan(PhieuDatRequest phieuDatRequest, IDatPhongKhachSan callback){
        ApiService.apiService.datPhongKhachSan(phieuDatRequest).enqueue(new Callback<ResultResponse>() {
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
