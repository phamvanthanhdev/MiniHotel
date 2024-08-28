package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.minihotel.adapter.HangPhongAdapter;
import com.minihotel.adapter.PhieuDatAdapter;
import com.minihotel.managers.CallPhieuDatsByIdKhachHang;
import com.minihotel.managers.interfaces.IPhieuDatsByIdKhachHang;
import com.minihotel.models.PhieuDat;
import com.minihotel.models.ThongTinHangPhong;
import com.minihotel.utils.Utils;

import java.util.List;

public class PhieuDatListActivity extends AppCompatActivity {
    private RecyclerView rcPhieuDat;
    private List<PhieuDat> phieuDats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_dat_list);

        initViews();
        setupBtnBack();
    }

    private void setupBtnBack(){
        ImageButton btnBack = findViewById(R.id.imageButton);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getPhieuDatByKhachHang(Utils.idKhachHang);
    }

    private void getPhieuDatByKhachHang(int idKhachHang) {
        CallPhieuDatsByIdKhachHang.getPhieuDatsByIdKhachHang(idKhachHang, new IPhieuDatsByIdKhachHang() {
            @Override
            public void onSuccess(List<PhieuDat> responses) {
                phieuDats = responses;
                setPhieuDatRecycler();
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(PhieuDatListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        rcPhieuDat = findViewById(R.id.rcPhieuDat);
    }

    private void setPhieuDatRecycler(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rcPhieuDat.setLayoutManager(layoutManager);
        PhieuDatAdapter phieuDatAdapter = new PhieuDatAdapter(this, phieuDats);
        rcPhieuDat.setAdapter(phieuDatAdapter);
        phieuDatAdapter.setOnItemClickListener(new PhieuDatAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(PhieuDatListActivity.this, PhieuDatDetailsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}