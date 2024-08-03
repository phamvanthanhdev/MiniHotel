package com.minihotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minihotel.R;
import com.minihotel.models.CartItem;
import com.minihotel.utils.Common;
import com.minihotel.utils.Utils;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.RecentsViewHolder> {
    Context context;
    List<CartItem> items;
    OnButtonTangClickListener onButtonTangClickListener;
    OnButtonGiamClickListener onButtonGiamClickListener;

    public CartAdapter(Context context, List<CartItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, null);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        layoutParams.setMargins(10, 0, 10, 20);
        view.setLayoutParams(layoutParams);
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        holder.txtTenHangPhong.setText(items.get(position).getTenHangPhong());
        holder.txtSoLuong.setText(items.get(position).getSoLuong() + "");
        holder.txtGiaPhong.setText(Common.convertCurrencyVietnamese(items.get(position).getGiaPhong()) + "VNĐ");
        long soNgayThue = Utils.calculateBetweenDate(Utils.ngayNhanPhong, Utils.ngayTraPhong);
        long tongTien = items.get(position).getGiaPhong() * items.get(position).getSoLuong() * soNgayThue;
        holder.txtTongTien.setText(Common.convertCurrencyVietnamese((int) tongTien) + "VNĐ");
        holder.imgHangPhong.setImageBitmap(Common.decodeBase64ToBitmap(items.get(position).getHinhAnh()));
        holder.txtSoLuongTrong.setText("Còn " + items.get(position).getSoLuongTrong() + " phòng");

        holder.btnTang.setOnClickListener(view ->
                onButtonTangClickListener.onClick(
                        position, items.get(position).getIdHangPhong(),
                        items.get(position).getSoLuong(), items.get(position).getSoLuongTrong()));
        holder.btnGiam.setOnClickListener(view ->
                onButtonGiamClickListener.onClick(
                        position, items.get(position).getIdHangPhong(), items.get(position).getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecentsViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHangPhong;
        TextView txtTenHangPhong, txtGiaPhong, txtTongTien, txtSoLuong, txtSoLuongTrong;
        TextView btnTang, btnGiam;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHangPhong = itemView.findViewById(R.id.imgHangPhong);
            txtTenHangPhong = itemView.findViewById(R.id.txtTenHangPhong);
            txtGiaPhong = itemView.findViewById(R.id.txtGiaPhong);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtSoLuongTrong = itemView.findViewById(R.id.txtSoLuongTrong);
            btnTang = itemView.findViewById(R.id.btnTang);
            btnGiam = itemView.findViewById(R.id.btnGiam);
        }
    }

    public void setOnButtonTangClickListener(OnButtonTangClickListener onButtonTangClickListener) {
        this.onButtonTangClickListener = onButtonTangClickListener;
    }

    public void setOnButtonGiamClickListener(OnButtonGiamClickListener onButtonGiamClickListener) {
        this.onButtonGiamClickListener = onButtonGiamClickListener;
    }

    public interface OnButtonTangClickListener {
        void onClick(int position, int id, int soLuong, int soLuongTrong);
    }

    public interface OnButtonGiamClickListener {
        void onClick(int position, int id, int soLuong);
    }
}
