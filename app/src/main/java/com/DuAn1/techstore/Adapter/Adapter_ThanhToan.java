package com.DuAn1.techstore.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DuAn1.techstore.Model.GioHang;
import com.DuAn1.techstore.Model.SanPham;

import java.util.ArrayList;

public class Adapter_ThanhToan extends RecyclerView.Adapter<Adapter_ThanhToan.ViewHolder> {
    private Context context;
    private ArrayList<SanPham> lstSP;
    private ArrayList<GioHang> lstGH;

    public Adapter_ThanhToan(Context context, ArrayList<SanPham> lstSP, ArrayList<GioHang> lstGH) {
        this.context = context;
        this.lstSP = lstSP;
        this.lstGH = lstGH;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
