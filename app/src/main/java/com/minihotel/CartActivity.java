package com.minihotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.minihotel.adapter.CartAdapter;
import com.minihotel.models.CartItem;
import com.minihotel.utils.Common;
import com.minihotel.utils.Utils;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rcCart;
    private List<CartItem> cartItems;
    private TextView txtTongTien, txtSoNgayThue, txtTongHangPhong;
    private int tongPhong = 0, tongHangPhong = 0;
    private int tongTien = 0;
    private Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartItems = Utils.cartItemsGlobal;


        initView();
        tinhSoLuongTongTien();
        showData();
        setEvents();
    }

    private void setEvents() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.cartItemsGlobal.size() <= 0){
                    Utils.onCreateMessageDialog(CartActivity.this,
                            "Vui lòng chọn hạng phòng trước khi đặt!").show();
                }else {
                    startActivity(new Intent(CartActivity.this, FormPhieuDatActivity.class));
                }
            }
        });
    }

    public void tinhSoLuongTongTien(){
        tongHangPhong = cartItems.size();
        tongPhong = Utils.getSoLuongPhongCart();
        tongTien = Utils.getTongTienCart();
    }

    private void showData() {
        long soNgayThue = Utils.calculateBetweenDate(Utils.ngayNhanPhong, Utils.ngayTraPhong);
        txtSoNgayThue.setText(soNgayThue+" ngày");
        txtTongHangPhong.setText(tongHangPhong + " hạng phòng");
        txtTongTien.setText(Common.convertCurrencyVietnamese(tongTien) + " VNĐ");
    }

    private void initView() {
        rcCart = findViewById(R.id.rcCart);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtSoNgayThue = findViewById(R.id.txtSoNgayThue);
        txtTongHangPhong = findViewById(R.id.txtTongHangPhong);
        btnXacNhan = findViewById(R.id.buttonXacNhan);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setHangPhongRecycler();
    }

    private void setHangPhongRecycler(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rcCart.setLayoutManager(layoutManager);
        CartAdapter cartAdapter = new CartAdapter(this, cartItems);
        rcCart.setAdapter(cartAdapter);
        cartAdapter.setOnButtonGiamClickListener(new CartAdapter.OnButtonGiamClickListener() {
            @Override
            public void onClick(int position, int id, int soLuong) {
                if(soLuong <= 1){
                    Utils.removeItemInCart(id);
                    cartAdapter.notifyItemRemoved(position);
                }else{
                    Utils.giamSoLuongCartItem(id);
                    cartAdapter.notifyItemChanged(position);
                }
                tinhSoLuongTongTien();
                showData();
            }
        });
        cartAdapter.setOnButtonTangClickListener(new CartAdapter.OnButtonTangClickListener() {
            @Override
            public void onClick(int position, int id, int soLuong, int soLuongTrong) {
                if((soLuong + 1) > soLuongTrong){
                    Utils.onCreateMessageDialog(CartActivity.this, "Số lượng phòng hiện tại không đủ!").show();
                }else {
                    Utils.tangSoLuongCartItem(id);
                    cartAdapter.notifyItemChanged(position);
                    tinhSoLuongTongTien();
                    showData();
                }
            }
        });

    }
}