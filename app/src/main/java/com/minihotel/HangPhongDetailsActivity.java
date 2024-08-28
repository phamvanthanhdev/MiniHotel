package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.minihotel.managers.CallThongTinHangPhongDetails;
import com.minihotel.managers.interfaces.IThongTinHangPhongDetails;
import com.minihotel.models.CartItem;
import com.minihotel.models.ThongTinHangPhong;
import com.minihotel.utils.Common;
import com.minihotel.utils.Utils;

public class HangPhongDetailsActivity extends AppCompatActivity {
    private ThongTinHangPhong hangPhong;
    private int id;
    private ImageView imgHangPhong;
    private TextView txtTenHangPhong, txtLoaiPhong, txtKieuPhong, txtGiaHienTai, txtGiaKhuyenMai, txtMoTa, txtSoLuongTrong, txtNgayNhanPhong, txtNgayTraPhong, txtSoNguoiToiDa;
    private Button btnDatPhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_phong_details);

        id = getIntent().getIntExtra("id", 0);
        initViews();
        setEvents();
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

    private void setEvents() {
        btnDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hangPhong.getSoLuongTrong() <= 0){
                    Utils.onCreateMessageDialog(HangPhongDetailsActivity.this,
                            "Hạng phòng hiện tại không còn đủ phòng để đặt!").show();
                }else {
                    int giaPhong;
                    if (hangPhong.getGiaKhuyenMai() == null || hangPhong.getGiaKhuyenMai() <= 0)
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
                    startActivity(new Intent(HangPhongDetailsActivity.this, CartActivity.class));
                }
            }
        });
    }

    private void initViews() {
        imgHangPhong = findViewById(R.id.imgHangPhong);
        txtTenHangPhong = findViewById(R.id.txtTenHangPhong);
        txtLoaiPhong = findViewById(R.id.txtLoaiPhong);
        txtKieuPhong = findViewById(R.id.txtKieuPhong);
        txtGiaHienTai = findViewById(R.id.txtGiaHienTai);
        txtGiaKhuyenMai = findViewById(R.id.txtGiaKhuyenMai);
        txtMoTa = findViewById(R.id.txtMoTa);
        btnDatPhong = findViewById(R.id.btnDatPhong);
        txtSoLuongTrong = findViewById(R.id.txtSoLuongTrong);
        txtNgayNhanPhong = findViewById(R.id.txtNgayNhanPhongText);
        txtNgayTraPhong = findViewById(R.id.txtNgayTraPhong);
        txtSoNguoiToiDa = findViewById(R.id.txtSoNguoiToiDa);
    }
    private void showData(){
        txtTenHangPhong.setText(hangPhong.getTenHangPhong());
        txtLoaiPhong.setText("Tên loại phòng: " + hangPhong.getTenLoaiPhong());
        txtKieuPhong.setText("Tên kiểu phòng: " + hangPhong.getTenKieuPhong());
        txtMoTa.setText("Mô tả: " + hangPhong.getMoTa());
        if(hangPhong.getGiaKhuyenMai() != null && hangPhong.getGiaKhuyenMai() > 0 ) {
            txtGiaKhuyenMai.setText(Common.convertCurrencyVietnamese(hangPhong.getGiaKhuyenMai())+ "VNĐ");
            txtGiaHienTai.setText(Common.convertCurrencyVietnamese(hangPhong.getGiaGoc())+ "VNĐ");
            txtGiaHienTai.setPaintFlags(txtGiaHienTai.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); //Gạch ngang text
        }else {
            txtGiaHienTai.setVisibility(View.INVISIBLE);
            txtGiaKhuyenMai.setText(Common.convertCurrencyVietnamese(hangPhong.getGiaGoc())+ "VNĐ");
        }
        imgHangPhong.setImageBitmap(Common.decodeBase64ToBitmap(hangPhong.getHinhAnh()));
        txtSoLuongTrong.setText("Chỉ còn " + hangPhong.getSoLuongTrong() + " phòng");
        txtNgayNhanPhong.setText(Utils.fommatDateShow(Utils.ngayNhanPhong));
        txtNgayTraPhong.setText(Utils.fommatDateShow(Utils.ngayTraPhong));
        txtSoNguoiToiDa.setText("Số người tối đa: " + hangPhong.getSoNguoiToiDa() + " người");
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(id != 0)
            getThongTinHangPhongDetails(id);
        else
            throw new RuntimeException("IdHangPhong not found.");
    }

    private void getThongTinHangPhongDetails(int id){
        String ngayDenDat = Utils.fommatDateRequest(Utils.ngayNhanPhong);
        String ngayDiDat = Utils.fommatDateRequest(Utils.ngayTraPhong);
        CallThongTinHangPhongDetails.getThongTinHangPhongById(id,ngayDenDat, ngayDiDat, new IThongTinHangPhongDetails() {
            @Override
            public void onSuccess(ThongTinHangPhong response) {
                hangPhong = response;
                showData();
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(HangPhongDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("AAA", t.getMessage());
            }
        });
    }

}