package com.DuAn1.techstore.Activity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.DuAn1.techstore.Adapter.Adapter_SP;
import com.DuAn1.techstore.DAO.Server;
import com.DuAn1.techstore.Model.GioHang;
import com.DuAn1.techstore.Model.Loading;
import com.DuAn1.techstore.Model.SanPham;
import com.DuAn1.techstore.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity_DienThoai extends AppCompatActivity {

    private static final int MATL = 1; // maLoai điện thoại :1
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<SanPham> lstSP;
    private Adapter_SP adapter_SP;
    private SanPham sanPham;
    private Loading loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);

        AnhXa();
        ActionBar();
        getDL_DT();
    }

    private void AnhXa() {
        loading = new Loading(this);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.rcv);
        lstSP = new ArrayList<>();
        adapter_SP = new Adapter_SP(getApplicationContext(), lstSP);
        loading.LoadingDialog();
        //
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter_SP);
    }

    private void getDL_DT() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.sanPhamTheoTL,
                response -> {
                    try {
                        loading.DimissDialog();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);//lay doi tuong thu i
                                sanPham = new SanPham();
                                sanPham.setMaSanPham(jsonObject.getInt("maSP"));
                                sanPham.setMaLoai(jsonObject.getInt("maLoai"));
                                sanPham.setTenSanPham(jsonObject.getString("tenSP"));
                                sanPham.setSoLuongNhap(jsonObject.getInt("soLuongNhap"));
                                sanPham.setHinhAnh(jsonObject.getString("hinhAnh"));
                                sanPham.setGiaTien(jsonObject.getInt("giaTien"));
                                sanPham.setGiaCu(jsonObject.getInt("giaCu"));
                                sanPham.setNgayNhap(jsonObject.getString("ngayNhap"));
                                sanPham.setThongTinSanPham(jsonObject.getString("thongTinSP"));
                                lstSP.add(sanPham);
                                adapter_SP.notifyDataSetChanged();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                },
                error -> Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("maLoai", String.valueOf(MATL));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    @SuppressLint("RestrictedApi")
    private void ActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Điện thoại");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        if (item.getItemId() == R.id.cart) {
            Intent intent = new Intent(getApplicationContext(), Activity_GioHang.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.Search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_SP.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_SP.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter_SP.fixMemoryLeak();
    }
}