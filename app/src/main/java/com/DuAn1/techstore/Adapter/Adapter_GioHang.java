package com.DuAn1.techstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.DuAn1.techstore.Model.GioHang;
import com.DuAn1.techstore.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class Adapter_GioHang extends RecyclerView.Adapter<Adapter_GioHang.ViewHolder> {
    private Context context;
    private ArrayList<GioHang> list;

    public Adapter_GioHang(Context context, ArrayList<GioHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_giohan, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView cardView;
        private ImageView imgSP;
        private TextView tvTenSp;
        private TextView tvGia;
        private AppCompatButton btnGiam;
        private TextView tvSoLuong;
        private AppCompatButton btnTang;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            imgSP = itemView.findViewById(R.id.imgSP);
            tvTenSp = itemView.findViewById(R.id.tvTenSp);
            tvGia = itemView.findViewById(R.id.tvGia);
            btnGiam = itemView.findViewById(R.id.btnGiam);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            btnTang = itemView.findViewById(R.id.btnTang);
        }
    }
}
