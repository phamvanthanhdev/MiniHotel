package com.minihotel.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minihotel.R;
import com.minihotel.models.ThongTinHangPhong;
import com.minihotel.utils.Common;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {
    Context context;
    List<ThongTinHangPhong> arrayList;
    OnItemClickListener onItemClickListener;

    public CarouselAdapter(Context context, List<ThongTinHangPhong> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(arrayList.get(position).getGiaKhuyenMai() != null && arrayList.get(position).getGiaKhuyenMai() > 0 ) {
            holder.txtGiaKhuyenMai.setText(Common.convertCurrencyVietnamese(arrayList.get(position).getGiaKhuyenMai())+ "VNĐ");
            holder.txtGiaHienTai.setText(Common.convertCurrencyVietnamese(arrayList.get(position).getGiaGoc()) + "VNĐ");
            holder.txtGiaHienTai.setPaintFlags(holder.txtGiaHienTai.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); //Gạch ngang text
        }else {
            holder.txtGiaHienTai.setVisibility(View.GONE);
            holder.txtGiaKhuyenMai.setText(Common.convertCurrencyVietnamese(arrayList.get(position).getGiaGoc()) + "VNĐ");
        }
        holder.imageView.setImageBitmap(Common.decodeBase64ToBitmap(arrayList.get(position).getHinhAnh()));
        holder.txtTenHangPhong.setText(arrayList.get(position).getTenHangPhong());
        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(arrayList.get(position).getIdHangPhong()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtGiaHienTai, txtGiaKhuyenMai, txtTenHangPhong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            txtGiaHienTai = itemView.findViewById(R.id.txtGiaHienTai);
            txtGiaKhuyenMai = itemView.findViewById(R.id.txtGiaKhuyenMai);
            txtTenHangPhong = itemView.findViewById(R.id.txtTenHangPhong);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int id);
    }
}
