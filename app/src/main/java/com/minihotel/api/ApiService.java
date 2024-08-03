package com.minihotel.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.minihotel.dto.PhieuDatRequest;
import com.minihotel.dto.ResultResponse;
import com.minihotel.models.ChiTietPhieuDat;
import com.minihotel.models.PhieuDat;
import com.minihotel.models.ThongTinHangPhong;
import com.minihotel.utils.Containts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(Containts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/thong-tin-hang-phong/all")
    Call<List<ThongTinHangPhong>> getAllThongTinHangPhong();

    @GET("api/thong-tin-hang-phong/so-luong/{id}")
    Call<ThongTinHangPhong> getThongTinHangPhongById(@Path("id") int id,
                                                     @Query("ngayDenDat") String ngayDenDat,
                                                     @Query("ngayDiDat") String ngayDiDat);

    @GET("api/phieu-dat/khach-hang/{id}")
    Call<List<PhieuDat>> getPhieuDatsByIdKhachhang(@Path("id") int id);

    @GET("api/phieu-dat/chi-tiet/{id}")
    Call<List<ChiTietPhieuDat>> getChiTietsByIdPhieuDat(@Path("id") int id);

    @GET("api/phieu-dat/{id}")
    Call<PhieuDat> getPhieuDatById(@Path("id") int id);

    @GET("api/thong-tin-hang-phong/tim-kiem")
    Call<List<ThongTinHangPhong>> timKiemThongTinHangPhong(@Query("ngayDenDat") String ngayDenDat,
                                                           @Query("ngayDiDat") String ngayDiDat,
                                                           @Query("soNguoi") Integer soNguoi,
                                                           @Query("giaMin") Long giaMin,
                                                           @Query("giaMax") Long giaMax);
    @GET("api/thong-tin-hang-phong/thoi-gian")
    Call<List<ThongTinHangPhong>> getHangPhongsTheoThoiGian(@Query("ngayDenDat") String ngayDenDat,
                                                            @Query("ngayDiDat") String ngayDiDat);

    @POST("api/phieu-dat/")
    Call<ResultResponse> datPhongKhachSan(@Body PhieuDatRequest phieuDatRequest);
}
