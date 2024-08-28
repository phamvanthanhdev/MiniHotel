package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.minihotel.managers.CallLogin;
import com.minihotel.managers.interfaces.ILogin;
import com.minihotel.models.KhachHang;
import com.minihotel.utils.Utils;

public class LoginActivity extends AppCompatActivity {
    private TextView edtTenDangNhap, edtMatKhau, txtDangKy;
    private Button btnDangNhap;
    private KhachHang khachHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setViews();
        setEvents();
    }

    private void login(String tenDangNhap, String matKhau){
        CallLogin.login(tenDangNhap, matKhau, new ILogin() {
            @Override
            public void onSuccess(KhachHang response) {
                if(response!= null){
                    khachHang = response;
                    Utils.idKhachHang = khachHang.getIdKhachHang();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Utils.onCreateMessageDialog(LoginActivity.this,
                            "Tên đăng nhập hoặc mật khẩu không chính xác").show();
                }
            }

            @Override
            public void onError(Throwable t) {
                Utils.onCreateMessageDialog(LoginActivity.this,
                        "Tên đăng nhập hoặc mật khẩu không chính xác").show();
            }
        });
    }

    private void setEvents() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDangNhap = edtTenDangNhap.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();
                if(tenDangNhap.equals("") || matKhau.equals("")){
                    Utils.onCreateMessageDialog(LoginActivity.this,
                            "Vui lòng nhập đầy đủ thông tin").show();
                }else{
                    login(tenDangNhap, matKhau);
                }
            }
        });
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DangKyActivity.class));
            }
        });
    }

    private void setViews() {
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        txtDangKy = findViewById(R.id.txtDangKy);
        btnDangNhap = findViewById(R.id.btnDangNhap);
    }
}