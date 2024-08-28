package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.minihotel.dto.KhachHangRequest;
import com.minihotel.managers.CallKhachHangById;
import com.minihotel.managers.CallCapNhatKhachHang;
import com.minihotel.managers.interfaces.ICapNhatKhachHang;
import com.minihotel.managers.interfaces.IGetKhachHangById;
import com.minihotel.models.KhachHang;
import com.minihotel.utils.Utils;

public class KhachHangActivity extends AppCompatActivity {
    private TextInputEditText edtCccd, edtHoTen, edtSdt, edtDiaChi, edtEmail;
    private Button btnThayDoi;
    private KhachHang khachHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);

        initViews();
        setEvents();
        setupBtnBack();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getKhachHangById(Utils.idKhachHang);
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
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cmnd = edtCccd.getText().toString().trim();
                String hoTen = edtHoTen.getText().toString().trim();
                String sdt = edtSdt.getText().toString().trim();
                String diaChi = edtDiaChi.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                if(cmnd.equals("") || hoTen.equals("") || sdt.equals("")
                    || email.equals("")){
                    Utils.onCreateMessageDialog(KhachHangActivity.this,
                            "Vui lòng nhập đầy đủ thông tin!").show();
                }else{
                    KhachHangRequest khachHangRequest =
                            new KhachHangRequest(cmnd, hoTen,sdt, diaChi, email);
                    capNhatKhachHang(Utils.idKhachHang, khachHangRequest);
                }
            }
        });
    }

    private void initViews() {
        edtCccd = findViewById(R.id.edtCccd);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtSdt = findViewById(R.id.edtSdt);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtEmail = findViewById(R.id.edtEmail);
        btnThayDoi = findViewById(R.id.btnThayDoi);
    }

    private void showDataKhachHang(){
        edtCccd.setText(khachHang.getCmnd());
        edtHoTen.setText(khachHang.getHoTen());
        edtSdt.setText(khachHang.getSdt());
        edtDiaChi.setText(khachHang.getDiaChi());
        edtEmail.setText(khachHang.getEmail());
    }

    private void getKhachHangById(int id){
        CallKhachHangById.getKhachHangById(id, new IGetKhachHangById() {
            @Override
            public void onSuccess(KhachHang response) {
                khachHang = response;
                showDataKhachHang();
            }

            @Override
            public void onError(Throwable t) {
                Log.d("TAG-ERR", t.getMessage());
                Toast.makeText(KhachHangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void capNhatKhachHang(int id, KhachHangRequest request){
        CallCapNhatKhachHang.capNhatKhachHang(id, request, new ICapNhatKhachHang() {
            @Override
            public void onSuccess(KhachHang khachHang) {
                Utils.onCreateMessageDialog(KhachHangActivity.this,
                        "Cập nhật thông tin thành công!").show();
                onResume();
            }

            @Override
            public void onError(Throwable t) {
                Utils.onCreateMessageDialog(KhachHangActivity.this,
                        "Lỗi cập nhật thông tin! Vui lòng thử lại").show();
            }
        });
    }
}