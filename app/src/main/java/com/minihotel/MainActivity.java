package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.minihotel.adapter.CarouselAdapter;
import com.minihotel.adapter.HangPhongAdapter;
import com.minihotel.managers.CallAllThongTinHangPhong;
import com.minihotel.managers.CallHangPhongTheoThoiGian;
import com.minihotel.managers.interfaces.IAllThongTinHangPhong;
import com.minihotel.managers.interfaces.IHangPhongsTheoThoiGian;
import com.minihotel.models.CartItem;
import com.minihotel.models.ThongTinHangPhong;
import com.minihotel.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcCarousel;
    private RecyclerView rcHangPhong;
    private LinearLayout layoutThoiGian;
    private TextView txtNgayNhanPhong, txtNgayTraPhong;
    private ProgressBar progressBar;
    private List<ThongTinHangPhong> thongTinHangPhongsCarousel, thongTinHangPhongs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
        setEvents();
        showData();
    }

    private void showData() {
        txtNgayNhanPhong.setText(Utils.fommatDateShow(Utils.ngayNhanPhong));
        txtNgayTraPhong.setText(Utils.fommatDateShow(Utils.ngayTraPhong));
    }

    private void setEvents() {
        layoutThoiGian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getAllHangPhong();
        getHangPhongTheoThoiGian(Utils.fommatDateRequest(Utils.ngayNhanPhong),
                                Utils.fommatDateRequest(Utils.ngayTraPhong));
    }

    public void getAllHangPhong(){
        CallAllThongTinHangPhong.getAllThongTinHangPhong(new IAllThongTinHangPhong() {
            @Override
            public void onSuccess(List<ThongTinHangPhong> thongTinHangPhongsResponse) {
                thongTinHangPhongsCarousel = thongTinHangPhongsResponse;
                setHangPhongCarouselRecycler();
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setHangPhongCarouselRecycler(){
        CarouselAdapter adapter = new CarouselAdapter(MainActivity.this, thongTinHangPhongsCarousel);
        rcCarousel.setAdapter(adapter);
        adapter.setOnItemClickListener(new CarouselAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(MainActivity.this, HangPhongDetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    public void getHangPhongTheoThoiGian(String ngayDenDat, String ngayDiDat){
        CallHangPhongTheoThoiGian.getHangPhongsTheoThoiGian(ngayDenDat, ngayDiDat ,new IHangPhongsTheoThoiGian() {
            @Override
            public void onSuccess(List<ThongTinHangPhong> responses) {
                thongTinHangPhongs = responses;
                progressBar.setVisibility(View.GONE);
                setHangPhongRecycler();
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setHangPhongRecycler(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rcHangPhong.setLayoutManager(layoutManager);
        HangPhongAdapter hangPhongAdapter = new HangPhongAdapter(this, thongTinHangPhongs);
        rcHangPhong.setAdapter(hangPhongAdapter);

        hangPhongAdapter.setOnButtonDatNgayClickListener(new HangPhongAdapter.OnButtonDatNgayClickListener() {
            @Override
            public void onClick(ThongTinHangPhong hangPhong) {
                if(hangPhong.getSoLuongTrong() <= 0){
                    Utils.onCreateMessageDialog(MainActivity.this,
                            "Hạng phòng không còn phòng trống").show();
                    return;
                }
                int giaPhong;
                if(hangPhong.getGiaKhuyenMai() == null || hangPhong.getGiaKhuyenMai() <= 0)
                    giaPhong = hangPhong.getGiaGoc();
                else
                    giaPhong = hangPhong.getGiaKhuyenMai();
                CartItem item = new CartItem(
                        hangPhong.getIdHangPhong(),
                        hangPhong.getTenHangPhong(),
                        hangPhong.getHinhAnh(),
                        giaPhong,
                        1,
                        hangPhong.getSoLuongTrong()
                );
                Utils.addItemToCart(item);
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        hangPhongAdapter.setOnItemClickListener(new HangPhongAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(MainActivity.this, HangPhongDetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        rcCarousel = findViewById(R.id.recycleViewCarousel);
        rcHangPhong = findViewById(R.id.rcHangPhong);
        layoutThoiGian = findViewById(R.id.layoutThoiGian);
        txtNgayNhanPhong = findViewById(R.id.txtNgayNhanPhongText);
        txtNgayTraPhong = findViewById(R.id.txtNgayTraPhong);
        progressBar = findViewById(R.id.progressBar);
    }


}