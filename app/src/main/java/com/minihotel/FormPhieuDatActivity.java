package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.minihotel.adapter.CartAdapter;
import com.minihotel.adapter.ThongTinCartItemAdapter;
import com.minihotel.dto.ChiTietPhieuDatRequest;
import com.minihotel.dto.KhachHangRequest;
import com.minihotel.dto.PhieuDatRequest;
import com.minihotel.dto.ResultResponse;
import com.minihotel.managers.PostDatPhongKhachSan;
import com.minihotel.managers.interfaces.IDatPhongKhachSan;
import com.minihotel.models.CartItem;
import com.minihotel.utils.Common;
import com.minihotel.utils.Utils;
import com.minihotel.zalopay.CreateOrder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class FormPhieuDatActivity extends AppCompatActivity {
    private TextInputEditText edtNgayBatDau, edtNgayTraPhong, edtLuuY, edtTienTamUng;
    private RadioGroup rgThanhToan;
    private TextInputEditText edtHoTen, edtSdt, edtEmail;
    private TextView txtTongTien;
    private Button btnXacNhan;
    private int tongTien = 0;
    private int tamUngDeXuat = 0;
    private RecyclerView rcCartItem;
    private PhieuDatRequest phieuDatRequest = new PhieuDatRequest();
    int typeThanhToan = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_phieu_dat);

        initViews();
        tinhTongTien();
        showData();
        setEvents();
        initZaloPay();
    }

    private void setEvents() {
        rgThanhToan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbZaloPay){
                    edtTienTamUng.setVisibility(View.VISIBLE);
                    typeThanhToan = 1;
                }
                if(checkedId == R.id.rbTienMat){
                    edtTienTamUng.setVisibility(View.GONE);
                    typeThanhToan = 0;
                }
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateSetData()){
                    phieuDatRequest.setIdKhachHang(Utils.khachHang.getIdKhachHang());

                    if(typeThanhToan == 0) { // Tien mat
                        Log.d("AAA", phieuDatRequest.toString());
                        datPhongKhachSan();
                    }else{                    // zalo pay
                        String tienTamUng = edtTienTamUng.getText().toString().trim();
                        try {
                            CreateOrder orderApi = new CreateOrder();
                            JSONObject data = orderApi.createOrder(tienTamUng);
                            String code = data.getString("return_code");
                            Toast.makeText(FormPhieuDatActivity.this, "return_code: " + code, Toast.LENGTH_LONG).show();
                            if (code.equals("1")) {
                                String token = data.getString("zp_trans_token");
                                ZaloPaySDK.getInstance().payOrder( FormPhieuDatActivity.this, token, "demozpdk://app", new PayOrderListener() {
                                    @Override
                                    public void onPaymentSucceeded(String s, String s1, String s2) {
                                        FormPhieuDatActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                //Hoan tat thanh toan
                                                Toast.makeText(FormPhieuDatActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
                                                //
                                                phieuDatRequest.setTienTamUng(Long.parseLong(tienTamUng));
                                                datPhongKhachSan();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onPaymentCanceled(String s, String s1) {
                                        Toast.makeText(FormPhieuDatActivity.this, "payment cancel!", Toast.LENGTH_SHORT).show();
                                        Utils.onCreateMessageDialog(FormPhieuDatActivity.this,
                                                "Thanh toán không thành công. Vui lòng thử lại sau!").show();
                                    }

                                    @Override
                                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                        Toast.makeText(FormPhieuDatActivity.this, "payment error!", Toast.LENGTH_SHORT).show();
                                        Utils.onCreateMessageDialog(FormPhieuDatActivity.this,
                                                "Xảy ra lỗi trong quá trình thanh toán. Vui lòng thử lại sau!").show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            Log.d("TAG-ERR", "err: " + e);
                            Toast.makeText(FormPhieuDatActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            Utils.onCreateMessageDialog(FormPhieuDatActivity.this,
                                    "Xảy ra lỗi trong quá trình thanh toán. Vui lòng thử lại sau!").show();
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void datPhongKhachSan(){
        PostDatPhongKhachSan.postDatPhongKhachSan(phieuDatRequest, new IDatPhongKhachSan() {
            @Override
            public void onSuccess(ResultResponse response) {
                Log.d("AAA", response.toString());
                Utils.cartItemsGlobal.clear();
                startActivity(new Intent(FormPhieuDatActivity.this, DatPhongThanhCongActivity.class));
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(FormPhieuDatActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateSetData() {
        if(edtNgayBatDau.getText().toString().trim().equals("") || 
                edtNgayTraPhong.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            phieuDatRequest.setNgayBatDau(Utils.fommatDateRequest(Utils.ngayNhanPhong));
            phieuDatRequest.setNgayTraPhong(Utils.fommatDateRequest(Utils.ngayTraPhong));
            phieuDatRequest.setNgayTao(Utils.fommatDateRequest(Utils.getCurrentDate()));
        }

        if(rgThanhToan.getCheckedRadioButtonId() == R.id.rbTienMat){
            phieuDatRequest.setTienTamUng(0L);
        }else if (rgThanhToan.getCheckedRadioButtonId() == R.id.rbZaloPay){
            phieuDatRequest.setTienTamUng(0L);
        }

        if(!edtLuuY.getText().toString().trim().equals(""))
            phieuDatRequest.setGhiChu(edtLuuY.getText().toString());

        if(typeThanhToan == 1){
            if(edtTienTamUng.getText().toString().trim().equals("")){
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                int tienTamUng;
                try {
                    tienTamUng = Integer.parseInt(edtTienTamUng.getText().toString().trim());
                }catch (Exception e){
                    Toast.makeText(this, "Vui lòng nhập số tiền hợp lệ!", Toast.LENGTH_SHORT).show();
                    Utils.onCreateMessageDialog(FormPhieuDatActivity.this,
                            "Vui lòng nhập số tiền hợp lệ!").show();
                    return false;
                }
                if(tienTamUng < tamUngDeXuat){
                    Toast.makeText(this, "Số tiền tạm ứng phải lớn hơn 10%!", Toast.LENGTH_SHORT).show();
                    Utils.onCreateMessageDialog(FormPhieuDatActivity.this,
                            "Số tiền tạm ứng phải lớn hơn 10%!").show();
                    return false;
                }
            }
        }


        List<ChiTietPhieuDatRequest> chiTietPhieuDatRequests = addChiTietPhieuDatRequest();
        if(chiTietPhieuDatRequests == null){
            return false;
        }
        phieuDatRequest.setChiTietRequests(chiTietPhieuDatRequests);
        return true;
    }

    public List<ChiTietPhieuDatRequest> addChiTietPhieuDatRequest(){
        List<ChiTietPhieuDatRequest> chiTietPhieuDatRequests = new ArrayList<>();
        if(Utils.cartItemsGlobal.size() <= 0) {
            Toast.makeText(this, "Chưa có hạng phòng nào được chọn!", Toast.LENGTH_SHORT).show();
            return null;
        }
        for (CartItem item: Utils.cartItemsGlobal) {
            chiTietPhieuDatRequests.add(new ChiTietPhieuDatRequest(item.getIdHangPhong(), item.getSoLuong()));
        }
        return chiTietPhieuDatRequests;
    }

    private void initViews() {
        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayTraPhong = findViewById(R.id.edtNgayTraPhong);
        edtLuuY = findViewById(R.id.edtLuuY);
        rgThanhToan = findViewById(R.id.rgThanhToan);

        txtTongTien = findViewById(R.id.txtTongTien);
        btnXacNhan = findViewById(R.id.buttonXacNhan);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtSdt = findViewById(R.id.edtSdt);
        edtEmail = findViewById(R.id.edtEmail);
        edtTienTamUng = findViewById(R.id.edtTienTamUng);

        rcCartItem = findViewById(R.id.rcCartItem);

        edtTienTamUng.setVisibility(View.GONE);
    }

    public void tinhTongTien(){
        tongTien = Utils.getTongTienCart();
        tamUngDeXuat = (int) ((int)tongTien * 0.1);
    }

    private void showData() {
        edtNgayBatDau.setText(Utils.fommatDateShow(Utils.ngayNhanPhong));
        edtNgayTraPhong.setText(Utils.fommatDateShow(Utils.ngayTraPhong));
        txtTongTien.setText(Common.convertCurrencyVietnamese(tongTien) + " VNĐ");


        edtHoTen.setText(Utils.khachHang.getHoTen());
        edtSdt.setText(Utils.khachHang.getSdt());
        edtEmail.setText(Utils.khachHang.getEmail());


        edtTienTamUng.setText(tamUngDeXuat+"");

        setCartItemRecycler();
    }

    // ============================ THANH TOÁN ZALO PAY ================

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
    private void setCartItemRecycler(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rcCartItem.setLayoutManager(layoutManager);
        ThongTinCartItemAdapter cartItemAdapter = new ThongTinCartItemAdapter(this, Utils.cartItemsGlobal);
        rcCartItem.setAdapter(cartItemAdapter);
    }

    private void initZaloPay() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2554, Environment.SANDBOX);
    }
}