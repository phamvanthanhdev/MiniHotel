package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DatPhongThanhCongActivity extends AppCompatActivity {
    private Button btnTrangChu, btnDanhSachPhieuDat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_phong_thanh_cong);

        initViews();
        setEvents();
    }

    private void setEvents() {
        btnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatPhongThanhCongActivity.this, MainActivity.class));
            }
        });

        btnDanhSachPhieuDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatPhongThanhCongActivity.this, PhieuDatListActivity.class));
            }
        });
    }

    private void initViews() {
        btnTrangChu = findViewById(R.id.buttonTrangChu);
        btnDanhSachPhieuDat = findViewById(R.id.buttonDanhSachPhieuDat);
    }
}