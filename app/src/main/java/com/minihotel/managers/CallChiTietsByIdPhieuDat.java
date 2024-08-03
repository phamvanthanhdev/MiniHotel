package com.minihotel.managers;

import com.minihotel.api.ApiService;
import com.minihotel.managers.interfaces.IChiTietsByIdPhieuDat;
import com.minihotel.managers.interfaces.IPhieuDatsByIdKhachHang;
import com.minihotel.models.ChiTietPhieuDat;
import com.minihotel.models.PhieuDat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallChiTietsByIdPhieuDat {
    public static void getChiTietsByIdPhieuDat(int idPhieuDat, IChiTietsByIdPhieuDat callback){
        ApiService.apiService.getChiTietsByIdPhieuDat(idPhieuDat).enqueue(new Callback<List<ChiTietPhieuDat>>() {
            @Override
            public void onResponse(Call<List<ChiTietPhieuDat>> call, Response<List<ChiTietPhieuDat>> response) {
                if(response.isSuccessful())
                    callback.onSuccess(response.body());
                else
                    callback.onError(new Throwable("Error code: " + response.code()));
            }

            @Override
            public void onFailure(Call<List<ChiTietPhieuDat>> call, Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }
}
