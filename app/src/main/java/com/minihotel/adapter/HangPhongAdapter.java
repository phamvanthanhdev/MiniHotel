package com.minihotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minihotel.R;
import com.minihotel.models.ThongTinHangPhong;
import com.minihotel.utils.Common;

import java.util.List;

public class HangPhongAdapter extends RecyclerView.Adapter<HangPhongAdapter.RecentsViewHolder> {
    Context context;
    List<ThongTinHangPhong> items;
    HangPhongAdapter.OnItemClickListener onItemClickListener;
    HangPhongAdapter.OnButtonDatNgayClickListener onButtonDatNgayClickListener;

    public HangPhongAdapter(Context context, List<ThongTinHangPhong> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hang_phong_item, null);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        layoutParams.setMargins(10, 0, 10, 30);
        view.setLayoutParams(layoutParams);
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        holder.txtSoLuongTrong.setText("Chỉ còn " + items.get(position).getSoLuongTrong() + " phòng");
        holder.txtTenHangPhong.setText(items.get(position).getTenHangPhong());
        holder.txtLoaiPhong.setText(items.get(position).getTenLoaiPhong());
        holder.txtKieuPhong.setText(items.get(position).getTenKieuPhong());
        if(items.get(position).getGiaKhuyenMai() != null && items.get(position).getGiaKhuyenMai() > 0 ) {
            holder.txtGiaKhuyenMai.setText(Common.convertCurrencyVietnamese(items.get(position).getGiaKhuyenMai())+ "VNĐ");
            holder.txtGiaHienTai.setText(Common.convertCurrencyVietnamese(items.get(position).getGiaGoc())+ "VNĐ");
            holder.txtGiaHienTai.setPaintFlags(holder.txtGiaHienTai.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); //Gạch ngang text
        }else {
            holder.txtGiaHienTai.setVisibility(View.INVISIBLE);
            holder.txtGiaKhuyenMai.setText(Common.convertCurrencyVietnamese(items.get(position).getGiaGoc())+ "VNĐ");
        }

        holder.txtTenHangPhong.setText(items.get(position).getTenHangPhong());

        holder.imgHangPhong.setImageBitmap(Common.decodeBase64ToBitmap(items.get(position).getHinhAnh()));

        holder.btnDatPhong.setOnClickListener(view ->
                onButtonDatNgayClickListener.onClick(items.get(position)));

        holder.itemView.setOnClickListener(view ->
                onItemClickListener.onClick(items.get(position).getIdHangPhong()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecentsViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHangPhong;
        TextView txtTenHangPhong, txtLoaiPhong, txtKieuPhong, txtGiaHienTai, txtGiaKhuyenMai, txtSoLuongTrong;
        Button btnDatPhong;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHangPhong = itemView.findViewById(R.id.imgHangPhong);
            txtTenHangPhong = itemView.findViewById(R.id.txtTenHangPhong);
            txtLoaiPhong = itemView.findViewById(R.id.txtLoaiPhong);
            txtKieuPhong = itemView.findViewById(R.id.txtKieuPhong);
            txtGiaHienTai = itemView.findViewById(R.id.txtGiaHienTai);
            txtGiaKhuyenMai = itemView.findViewById(R.id.txtGiaKhuyenMai);
            btnDatPhong = itemView.findViewById(R.id.btnDatPhong);
            txtSoLuongTrong = itemView.findViewById(R.id.txtSoLuongTrong);
        }
    }

    public void setOnButtonDatNgayClickListener(HangPhongAdapter.OnButtonDatNgayClickListener onButtonDatNgayClickListener) {
        this.onButtonDatNgayClickListener = onButtonDatNgayClickListener;
    }

    public void setOnItemClickListener(HangPhongAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int id);
    }

    public interface OnButtonDatNgayClickListener {
        void onClick(ThongTinHangPhong hangPhong);
    }
}
