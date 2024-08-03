package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.minihotel.adapter.ChiTietPhieuDatAdapter;
import com.minihotel.managers.CallChiTietsByIdPhieuDat;
import com.minihotel.managers.CallPhieuDatById;
import com.minihotel.managers.interfaces.IChiTietsByIdPhieuDat;
import com.minihotel.managers.interfaces.IPhieuDatById;
import com.minihotel.models.ChiTietPhieuDat;
import com.minihotel.models.PhieuDat;
import com.minihotel.utils.Common;
import com.minihotel.utils.Utils;

import java.time.LocalDate;
import java.util.List;

public class PhieuDatDetailsActivity extends AppCompatActivity {
    private TextView txtIdPhieuDat, txtNgayNhanPhong, txtNgayTraPhong, txtNgayTao, txtTamUng, txtTrangThai;
    private RecyclerView rcChiTiets;
    private PhieuDat phieuDat;
    private List<ChiTietPhieuDat> chiTietPhieuDats;
    int idPhieuDat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_dat_details);

        Intent intent = getIntent();
        idPhieuDat = intent.getIntExtra("id", 0);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPhieuDatById();
        getChiTietPhieuDats();
    }

    private void showData() {
        txtIdPhieuDat.setText(phieuDat.getIdPhieuDat()+"");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txtNgayNhanPhong.setText(Utils.fommatDateShow(LocalDate.parse(phieuDat.getNgayBatDau())));
            txtNgayTraPhong.setText(Utils.fommatDateShow(LocalDate.parse(phieuDat.getNgayTraPhong())));
            txtNgayTao.setText(Utils.fommatDateShow(LocalDate.parse(phieuDat.getNgayTao())));
        }

        if(phieuDat.getTienTamUng() > 0)
            txtTamUng.setText(Common.convertCurrencyVietnamese(phieuDat.getTienTamUng()) + " VNĐ");
        else
            txtTamUng.setText("Chưa tạm ứng");
        if(phieuDat.getTrangThaiHuy()) {
            txtTrangThai.setText("Đã hủy");
        } else {
            if(phieuDat.getIdNhanVien() != null)
                txtTrangThai.setText("Hoàn tất");
            else
                txtTrangThai.setText("Chờ xử lý");
        }
    }

    private void getChiTietPhieuDats(){
        CallChiTietsByIdPhieuDat.getChiTietsByIdPhieuDat(idPhieuDat, new IChiTietsByIdPhieuDat() {
            @Override
            public void onSuccess(List<ChiTietPhieuDat> responses) {
                Log.d("AAA", responses.size() + "size ctpd");
                chiTietPhieuDats = responses;
                setCartItemRecycler();
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(PhieuDatDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCartItemRecycler(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rcChiTiets.setLayoutManager(layoutManager);
        ChiTietPhieuDatAdapter adapter = new ChiTietPhieuDatAdapter(this, chiTietPhieuDats);
        rcChiTiets.setAdapter(adapter);
    }

    private void getPhieuDatById(){
        CallPhieuDatById.getPhieuDatById(idPhieuDat, new IPhieuDatById() {
            @Override
            public void onSuccess(PhieuDat phieuDatResponse) {
                phieuDat = phieuDatResponse;
                showData();
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(PhieuDatDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        txtIdPhieuDat = findViewById(R.id.txtIdPhieuDat);
        txtNgayNhanPhong = findViewById(R.id.txtNgayNhanPhong);
        txtNgayTraPhong = findViewById(R.id.txtNgayTraPhong);
        txtNgayTao = findViewById(R.id.txtNgayTao);
        txtTamUng = findViewById(R.id.txtTamUng);
        txtTrangThai = findViewById(R.id.txtTrangThai);
        rcChiTiets = findViewById(R.id.rcChiTiets);
    }


}