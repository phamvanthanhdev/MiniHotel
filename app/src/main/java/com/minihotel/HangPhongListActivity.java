package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.minihotel.adapter.CartAdapter;
import com.minihotel.adapter.HangPhongAdapter;
import com.minihotel.managers.CallAllThongTinHangPhong;
import com.minihotel.managers.CallTimKiemThongTinHangPhong;
import com.minihotel.managers.interfaces.IAllThongTinHangPhong;
import com.minihotel.managers.interfaces.ITimKiemThongTinHangPhong;
import com.minihotel.models.CartItem;
import com.minihotel.models.ThongTinHangPhong;
import com.minihotel.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class HangPhongListActivity extends AppCompatActivity {
    private RecyclerView rcHangPhong;
    private List<ThongTinHangPhong> thongTinHangPhongs;
    private int soNguoi;
    private long giaMin;
    private long giaMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_phong_list);

        Intent intent = getIntent();
        soNguoi = intent.getIntExtra("soNguoi", 0);
        giaMin = intent.getLongExtra("giaMin", 0);
        giaMax = intent.getLongExtra("giaMax", 0);

        initViews();
    }

    private void initViews() {
        rcHangPhong = findViewById(R.id.recycleViewHangPhong);
    }

    @Override
    protected void onResume() {
        super.onResume();

        timKiemThongTinHangPhong();
    }

    public void timKiemThongTinHangPhong(){
        String ngayDenDat = Utils.fommatDateRequest(Utils.ngayNhanPhong);
        String ngayDiDat = Utils.fommatDateRequest(Utils.ngayTraPhong);
        CallTimKiemThongTinHangPhong.timKiemThongTinHangPhong(ngayDenDat, ngayDiDat, soNguoi, giaMin, giaMax, new ITimKiemThongTinHangPhong() {
            @Override
            public void onSuccess(List<ThongTinHangPhong> thongTinHangPhongsResponse) {
                if(thongTinHangPhongsResponse.size() <= 0){
                    Utils.onCreateMessageDialog(HangPhongListActivity.this,
                            "Không có hạng phòng nào thỏa mãn!").show();
                    return;
                }
                thongTinHangPhongs = thongTinHangPhongsResponse;
                setHangPhongRecycler();
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(HangPhongListActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(HangPhongListActivity.this, CartActivity.class));
            }
        });
        hangPhongAdapter.setOnItemClickListener(new HangPhongAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(HangPhongListActivity.this, HangPhongDetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}