package com.minihotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minihotel.R;
import com.minihotel.models.CartItem;
import com.minihotel.models.ChiTietPhieuDat;
import com.minihotel.utils.Common;

import java.util.List;

public class ChiTietPhieuDatAdapter extends RecyclerView.Adapter<ChiTietPhieuDatAdapter.RecentsViewHolder> {
    Context context;
    List<ChiTietPhieuDat> items;

    public ChiTietPhieuDatAdapter(Context context, List<ChiTietPhieuDat> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chitiet_phieudat_item, null);
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
        holder.txtSoLuong.setText(items.get(position).getSoLuong() + " phòng");
        holder.txtGiaPhong.setText(Common.convertCurrencyVietnamese(items.get(position).getDonGia()) + "VNĐ/");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecentsViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenHangPhong, txtGiaPhong, txtSoLuong;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenHangPhong = itemView.findViewById(R.id.txtTenHangPhong);
            txtGiaPhong = itemView.findViewById(R.id.txtGiaPhong);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
        }
    }
}
