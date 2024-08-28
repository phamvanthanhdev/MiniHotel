package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.minihotel.dto.DangKyRequest;
import com.minihotel.dto.ResultResponse;
import com.minihotel.managers.CallDangKy;
import com.minihotel.managers.interfaces.IDangKy;
import com.minihotel.utils.Utils;

public class DangKyActivity extends AppCompatActivity {
    private TextInputEditText edtEmail, edtHoTen, edtDiaChi, edtSdt, edtCccd, edtTenDangNhap, edtMatKhau;
    private Button btnDangKy;
    private TextView txtDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        setViews();
        setEvents();
    }

    private void setEvents() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String hoTen = edtHoTen.getText().toString().trim();
                String diaChi = edtDiaChi.getText().toString().trim();
                String sdt = edtSdt.getText().toString().trim();
                String cccd = edtCccd.getText().toString().trim();
                String tenDangNhap = edtTenDangNhap.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();

                if(email.equals("") || hoTen.equals("") || sdt.equals("") || cccd.equals("")
                        || tenDangNhap.equals("") || matKhau.equals("")){
                    Utils.onCreateMessageDialog(DangKyActivity.this, "Vui lòng nhập đầy đủ thông tin.").show();
                    return;
                }
                DangKyRequest dangKyRequest = new DangKyRequest(
                        cccd, hoTen, sdt,
                        diaChi.equals("") ? null : diaChi,
                        email, tenDangNhap, matKhau
                );
                dangKyTaiKhoan(dangKyRequest);
            }
        });

        txtDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKyActivity.this, LoginActivity.class));
            }
        });
    }

    private void dangKyTaiKhoan(DangKyRequest dangKyRequest){
        CallDangKy.dangKyTaiKhoan(dangKyRequest, new IDangKy() {
            @Override
            public void onSuccess(ResultResponse response) {
                if(response.getCode() == 200){
                    Utils.onCreateMessageDialog(DangKyActivity.this,
                            response.getMessage()).show();
                }else if(response.getCode() == 400){
                    Utils.onCreateMessageDialog(DangKyActivity.this,
                            response.getMessage()).show();
                }
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(DangKyActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Utils.onCreateMessageDialog(DangKyActivity.this,
                        "Lỗi đăng ký tài khoản.").show();
            }
        });
    }

    private void setViews() {
        edtEmail = findViewById(R.id.edtEmail);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSdt = findViewById(R.id.edtSdt);
        edtCccd = findViewById(R.id.edtCccd);
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);

        btnDangKy = findViewById(R.id.btnDangKy);
        txtDangNhap = findViewById(R.id.txtDangNhap);
    }
}