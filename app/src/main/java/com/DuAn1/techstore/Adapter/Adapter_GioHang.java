package com.DuAn1.techstore.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.DuAn1.techstore.Activity.Activity_GioHang;
import com.DuAn1.techstore.DAO.Server;
import com.DuAn1.techstore.Model.GioHang;
import com.DuAn1.techstore.Model.Loading;
import com.DuAn1.techstore.Model.SanPham;
import com.DuAn1.techstore.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Adapter_GioHang extends RecyclerView.Adapter<Adapter_GioHang.ViewHolder> {

    private final Context context;
    private final Activity_GioHang activity_gioHang;
    private final ArrayList<GioHang> lstGH;
    private final ArrayList<SanPham> lstSP;
    private final DecimalFormat format = new DecimalFormat("###,###,###");
    private int maKH;
    private final ViewBinderHelper binderHelper = new ViewBinderHelper();
    private final Loading loading;

    public Adapter_GioHang(Context context, Activity_GioHang activity_gioHang, ArrayList<GioHang> lstGH, ArrayList<SanPham> lstSP) {
        this.context = context;
        this.activity_gioHang = activity_gioHang;
        this.lstGH = lstGH;
        this.lstSP = lstSP;
        loading = new Loading((Activity) context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_giohan, null, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        GioHang gioHang = lstGH.get(position);
        SanPham sanPham = lstSP.get(position);
        if (gioHang != null && sanPham != null) {
            Picasso.get()
                    .load(sanPham.getHinhAnh())
                    .placeholder(R.drawable.dongho)
                    .error(R.drawable.atvphone)
                    .into(holder.imgSP);
            holder.tvTenSp.setText(sanPham.getTenSanPham());

            holder.tvGia.setText(format.format(sanPham.getGiaTien()) + "đ");
            holder.tvSoLuong.setText("Số lượng mua: " + gioHang.getSoLuongMua());
            getThongTinKH();

            binderHelper.bind(holder.SwipeRevealLayout, String.valueOf(sanPham.getMaSanPham()));
            holder.linerDelete.setOnClickListener(view -> {
                lstSP.remove(holder.getAdapterPosition());
                lstGH.remove(holder.getAdapterPosition());
                xoaSanPhamKhoiGioHang(sanPham.getMaSanPham(), maKH);
                notifyDataSetChanged();
                if (lstGH.size()==0 && lstSP.size()==0) {
                    activity_gioHang.xuLiKhiGHRong();
                }
            });
        }
    }

    public void xoaSanPhamKhoiGioHang(int maSanPham, int maKH) {
        StringRequest request = new StringRequest(Request.Method.POST, Server.deleteSanPhamGH,
                response -> {
                    switch (response) {
                        case "success": {
                            loading.DimissDialog();
                            Toast.makeText(context, "Đã xóa!", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case "failure": {
                            Toast.makeText(context, "Lỗi xóa", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }, error -> Toast.makeText(context, "Lỗi kết nối!", Toast.LENGTH_SHORT).show()) {
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("maSP", String.valueOf(maSanPham));
                params.put("maKH", String.valueOf(maKH));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);

    }


    private void getThongTinKH() {
        SharedPreferences preferences = context.getSharedPreferences("Luu_dangNhap", Context.MODE_PRIVATE);
        String userName = preferences.getString("USER", "");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getKhachHang,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        maKH = jsonObject.getInt("maKH");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("tenDangNhap", userName);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    @Override
    public int getItemCount() {
        if (lstGH != null && lstSP != null) {
            return lstGH.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgSP;
        private final TextView tvTenSp;
        private final TextView tvGia;
        private final TextView tvSoLuong;
        private final com.chauthai.swipereveallayout.SwipeRevealLayout SwipeRevealLayout;
        private final LinearLayout linerDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSP = itemView.findViewById(R.id.imgSP);
            tvTenSp = itemView.findViewById(R.id.tvTenSp);
            tvGia = itemView.findViewById(R.id.tvGia);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuongMua);
            //
            SwipeRevealLayout = itemView.findViewById(R.id.SwipeRevealLayout);
            linerDelete = itemView.findViewById(R.id.linerDelete);
        }
    }
}
