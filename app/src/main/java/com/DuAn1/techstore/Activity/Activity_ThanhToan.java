package com.DuAn1.techstore.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.DuAn1.techstore.Adapter.Adapter_GioHang;
import com.DuAn1.techstore.DAO.Server;
import com.DuAn1.techstore.Model.GioHang;
import com.DuAn1.techstore.Model.KhachHang;
import com.DuAn1.techstore.Model.Loading;
import com.DuAn1.techstore.Model.SanPham;
import com.DuAn1.techstore.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_ThanhToan extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTongTien;
    private Button btnThanhToan;
    private ImageView img;
    private TextView tvTenSp;
    private TextView tvGia;


    private TextView tvThongTin;
    private RecyclerView rcv;


    private SanPham sanPham;
    private KhachHang khachHang;
    private String userName;
    private Loading loading;
    int sl;
    int maHD;
    private int tongTien;
    //
    private ArrayList<GioHang> lstGH;
    private ArrayList<SanPham> lstSP;
    private Adapter_GioHang adapter_gioHang;
    private final DecimalFormat format = new DecimalFormat("###,###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        AnhXa();
        loading.LoadingDialog();
        ActionBar();
        getDl();
        getThongTinKH();
        btnThanhToan.setOnClickListener(view -> {
            loading.LoadingDialog();
            insertHoaDon();

        });


    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar);
        img = findViewById(R.id.img);
        tvTenSp = findViewById(R.id.tvTenSp);
        tvGia = findViewById(R.id.tvGia);
        tvThongTin = findViewById(R.id.tvThongTin);
        tvTongTien = findViewById(R.id.tvTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        loading = new Loading(this);
        khachHang = new KhachHang();
        //
        rcv = findViewById(R.id.rcv);

    }

    private void getDl() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        sanPham = (SanPham) bundle.get("sanPham");
        sl = bundle.getInt("sl");
        //
        lstSP = (ArrayList<SanPham>) bundle.get("lstSP");
        lstGH = (ArrayList<GioHang>) bundle.get("lstGH");

        tongTien = bundle.getInt("tongTien");

//        adapter_gioHang = new Adapter_GioHang(Activity_ThanhToan.this,this, lstGH, lstSP);
//        LinearLayoutManager manager = new LinearLayoutManager(Activity_ThanhToan.this);
//        rcv.setLayoutManager(manager);
//        rcv.setAdapter(adapter_gioHang);
    }

    @SuppressLint("SetTextI18n")
    private void setThongtin() {
        if (sanPham != null) { // khi mua trực tiếp k add vào giỏ hàng
            Picasso.get()
                    .load(sanPham.getHinhAnh())
                    .placeholder(R.drawable.dongho)
                    .error(R.drawable.atvphone)
                    .into(img);
            tvGia.setText(format.format(sanPham.getGiaTien()) + "đ");
            tvTenSp.setText(sanPham.getTenSanPham());
            tvThongTin.setText("Tên khách hàng: " + khachHang.getTenKhachHang() + "\n"
                    + "Địa chỉ: " + khachHang.getDiaChi() + "\n"
                    + "Số điện thoại: " + khachHang.getSoDienThoai() + "\n"
                    + "Số lượng: " + sl);
        } else if (lstSP != null && lstGH != null) { // khi thanh toán từ giỏ hàng
            img.setVisibility(View.INVISIBLE);
            tvGia.setVisibility(View.INVISIBLE);
            tvTenSp.setVisibility(View.INVISIBLE);
            for (int i = 0; i < lstSP.size(); i++) {
                sl += lstGH.get(i).getSoLuongMua();
            }
            tvThongTin.setText("Tên khách hàng: " + khachHang.getTenKhachHang() + "\n"
                    + "Địa chỉ: " + khachHang.getDiaChi() + "\n"
                    + "Số điện thoại: " + khachHang.getSoDienThoai() + "\n"
                    + "Tổng số lượng sản phẩm: " + sl);

        }
        tvTongTien.setText("Tổng tiền: " + format.format(tongTien) + "đ");
    }

    private void insertHoaDon() {
        StringRequest request = new StringRequest(Request.Method.POST, Server.insertHoaDon, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                maHD = jsonArray.getInt(0);
                loading.DimissDialog();
                insertChiTietHoaDon(maHD);
                DiaLog("Thanh toán thành công!" + "\n"
                        + "Hàng sẽ được vận chuyển đến bạn sớm nhất!");
                //Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(getApplicationContext(), "Loix ket noi!", Toast.LENGTH_SHORT).show()) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("maKH", String.valueOf(khachHang.getMaKhachHang()));
                params.put("tongTien", String.valueOf(tongTien));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    private void insertChiTietHoaDon(int maHD) { // khi thanh toán từ giỏ hàng
        if (lstSP != null) {
            for (int i = 0; i < lstSP.size(); i++) {
                int indexSp = i;
                int updateSoLuongSp = lstSP.get(indexSp).getSoLuongNhap() - lstGH.get(indexSp).getSoLuongMua();
                StringRequest request = new StringRequest(Request.Method.POST, Server.insertChiTietHoaDon,
                        response -> {
                            switch (response) {
                                case "\nsuccess": {
                                    //Toast.makeText(getApplicationContext(), "Insert chi tiet hoa don thanh cong", Toast.LENGTH_SHORT).show();
                                    UpdateSanPham(lstSP.get(indexSp).getMaSanPham(), updateSoLuongSp);
                                    break;
                                }
                                case "failure": {
                                    Toast.makeText(getApplicationContext(), "loi insert chi tiet hoa don thanh cong", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                        },
                        error -> Toast.makeText(getApplicationContext(), "Looix ket noi", Toast.LENGTH_SHORT).show()) {
                    @NonNull
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<>();
                        params.put("maHD", String.valueOf(maHD));
                        params.put("maSP", String.valueOf(lstSP.get(indexSp).getMaSanPham()));
                        params.put("soLuongMua", String.valueOf(lstGH.get(indexSp).getSoLuongMua()));
                        params.put("donGia", String.valueOf(lstSP.get(indexSp).getGiaTien()));
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(request);
            }
        } else if (sanPham != null) { // khi thanh toán ngay
            int updateSoLuongSp = sanPham.getSoLuongNhap() - sl;
            StringRequest request = new StringRequest(Request.Method.POST, Server.insertChiTietHoaDon,
                    response -> {
                        switch (response) {
                            case "\nsuccess": {
                                Toast.makeText(getApplicationContext(), "Insert chi tiet hoa don thanh cong", Toast.LENGTH_SHORT).show();
                                UpdateSanPham(sanPham.getMaSanPham(), updateSoLuongSp);
                                break;
                            }
                            case "failure": {
                                Toast.makeText(getApplicationContext(), "loi insert chi tiet hoa don thanh cong", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    },
                    error -> Toast.makeText(getApplicationContext(), "Looix ket noi", Toast.LENGTH_SHORT).show()) {
                @NonNull
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("maHD", String.valueOf(maHD));
                    params.put("maSP", String.valueOf(sanPham.getMaSanPham()));
                    params.put("soLuongMua", String.valueOf(sl));
                    params.put("donGia", String.valueOf(sanPham.getGiaTien()));
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }
    }

    private void UpdateSanPham(int maSp, int soLuongUpdate) {
        StringRequest request = new StringRequest(Request.Method.POST, Server.updateSanPham, response -> {
            switch (response) {
                case "failure": {
                    Toast.makeText(getApplicationContext(), "Loix update bang sanpham", Toast.LENGTH_SHORT).show();
                    break;
                }
                case "sucess": {
                    Log.d("updateSP", "UpdateSanPham: thanh cong");
                }
            }

        }, error -> Toast.makeText(getApplicationContext(), "Loix ket noi!", Toast.LENGTH_SHORT).show()) {
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("maSP", String.valueOf(maSp));
                params.put("soLuongNhap", String.valueOf(soLuongUpdate));
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }


    private void DiaLog(String mess) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(Activity_ThanhToan.this).inflate(R.layout.dialog_thanhtoan, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        TextView tvMess;
        Button btnOK;
        btnOK = view.findViewById(R.id.btnOK);
        tvMess = view.findViewById(R.id.tvMess);
        //
        tvMess.setText(mess);
        btnOK.setOnClickListener(view1 ->
        {
            alertDialog.dismiss();
            if (lstGH != null) {
                deleteGioHang();
            }
            Activity_ThanhToan.super.onBackPressed();
        });

        alertDialog.show();
    }

    private void deleteGioHang() {
        StringRequest request = new StringRequest(Request.Method.POST, Server.deleteGioHang, response -> {
            switch (response) {
                case "failure": {
                    Toast.makeText(getApplicationContext(), "xoa loi xoa", Toast.LENGTH_SHORT).show();
                    break;
                }
                case "success": {
                    Toast.makeText(getApplicationContext(), "Xoa thang cong", Toast.LENGTH_SHORT).show();
                    Log.d("updateSP", "UpdateSanPham: thanh cong");
                    break;
                }
            }

        }, error -> Toast.makeText(getApplicationContext(), "Loix ket noi!", Toast.LENGTH_SHORT).show()) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("maKH", String.valueOf(khachHang.getMaKhachHang()));
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }


    private void getThongTinKH() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Accout_file", Context.MODE_PRIVATE);
        userName = preferences.getString("USER", "");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getKhachHang,
                response -> {
                    loading.DimissDialog();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        khachHang.setMaKhachHang(jsonObject.getInt("maKH"));
                        khachHang.setUsername(jsonObject.getString("tenDangNhap"));
                        khachHang.setPassword(jsonObject.getString("matKhau"));
                        khachHang.setTenKhachHang(jsonObject.getString("tenKH"));
                        khachHang.setNamSinh(jsonObject.getString("namSinh"));
                        khachHang.setSoDienThoai(jsonObject.getString("soDienThoai"));
                        khachHang.setDiaChi(jsonObject.getString("diaChi"));
                        setThongtin();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("tenDangNhap", userName);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    private void ActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Thanh toán");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}