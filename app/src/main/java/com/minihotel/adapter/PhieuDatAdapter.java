package com.minihotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.minihotel.R;
import com.minihotel.models.PhieuDat;
import com.minihotel.utils.Common;

import java.util.List;

public class PhieuDatAdapter extends RecyclerView.Adapter<PhieuDatAdapter.RecentsViewHolder> {
    Context context;
    List<PhieuDat> items;
    OnItemClickListener onItemClickListener;
    public PhieuDatAdapter(Context context, List<PhieuDat> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.phieu_dat_item, null);
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
        holder.txtIdPhieuDat.setText(items.get(position).getIdPhieuDat() + "");
        holder.txtNgayTraPhong.setText(items.get(position).getNgayTraPhong());
        holder.txtNgayBatDau.setText(items.get(position).getNgayBatDau());

        if(items.get(position).getTienTamUng() > 0)
            holder.txtTamUng.setText(Common.convertCurrencyVietnamese(items.get(position).getTienTamUng()) + " VNĐ");
        else
            holder.txtTamUng.setText("Chưa tạm ứng");
        if(items.get(position).getTrangThaiHuy()) {
            holder.txtTrangThai.setText("Đã hủy");
        } else {
            if(items.get(position).getIdNhanVien() != null)
                holder.txtTrangThai.setText("Hoàn tất");
            else
                holder.txtTrangThai.setText("Chờ xử lý");
        }

        holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(items.get(position).getIdPhieuDat()));
        holder.btnChiTiet.setOnClickListener(view -> onItemClickListener.onClick(items.get(position).getIdPhieuDat()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecentsViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdPhieuDat, txtNgayTraPhong, txtNgayBatDau, txtTamUng, txtTrangThai;
        Button btnChiTiet;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdPhieuDat = itemView.findViewById(R.id.txtIdPhieuDat);
            txtNgayTraPhong = itemView.findViewById(R.id.txtNgayTraPhong);
            txtNgayBatDau = itemView.findViewById(R.id.txtNgayNhanPhongText);
            txtTamUng = itemView.findViewById(R.id.txtTamUng);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            btnChiTiet = itemView.findViewById(R.id.btnChiTiet);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int id);
    }
}
