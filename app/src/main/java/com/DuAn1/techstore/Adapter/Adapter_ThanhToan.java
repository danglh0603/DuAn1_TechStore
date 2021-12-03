package com.DuAn1.techstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DuAn1.techstore.Model.GioHang;
import com.DuAn1.techstore.Model.SanPham;
import com.DuAn1.techstore.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_ThanhToan extends RecyclerView.Adapter<Adapter_ThanhToan.ViewHolder> {
    private Context context;
    private ArrayList<SanPham> lstSP;
    private ArrayList<GioHang> lstGH;
    private DecimalFormat format = new DecimalFormat("###,###,###");

    public Adapter_ThanhToan(Context context, ArrayList<SanPham> lstSP, ArrayList<GioHang> lstGH) {
        this.context = context;
        this.lstSP = lstSP;
        this.lstGH = lstGH;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_thanhtoan, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham = lstSP.get(position);
        GioHang gioHang = lstGH.get(position);
        if (sanPham != null) {
            Picasso.get()
                    .load(sanPham.getHinhAnh())
                    .placeholder(R.drawable.dongho)
                    .error(R.drawable.atvphone)
                    .into(holder.imgSP);
            holder.tvTenSp.setText(sanPham.getTenSanPham());
            holder.tvGia.setText(format.format(sanPham.getGiaTien()) + "đ");
            holder.tvSoLuongMua.setText("Số lượng mua: " + gioHang.getSoLuongMua());
        }
    }

    @Override
    public int getItemCount() {
        if (lstSP != null) {
            return lstSP.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgSP;
        private TextView tvTenSp;
        private TextView tvGia;
        private TextView tvSoLuongMua;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSP = itemView.findViewById(R.id.imgSP);
            tvTenSp = itemView.findViewById(R.id.tvTenSp);
            tvGia = itemView.findViewById(R.id.tvGia);
            tvSoLuongMua = itemView.findViewById(R.id.tvSoLuongMua);
        }
    }
}
