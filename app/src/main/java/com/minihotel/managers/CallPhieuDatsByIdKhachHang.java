package com.minihotel.managers;

import com.minihotel.api.ApiService;
import com.minihotel.managers.interfaces.IPhieuDatsByIdKhachHang;
import com.minihotel.models.PhieuDat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallPhieuDatsByIdKhachHang {
    public static void getPhieuDatsByIdKhachHang(int idKhachHang, IPhieuDatsByIdKhachHang callback){
        ApiService.apiService.getPhieuDatsByIdKhachhang(idKhachHang).enqueue(new Callback<List<PhieuDat>>() {
            @Override
            public void onResponse(Call<List<PhieuDat>> call, Response<List<PhieuDat>> response) {
                if(response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onError(new Throwable("Error code: " + response.code()));
            }

            @Override
            public void onFailure(Call<List<PhieuDat>> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
}
