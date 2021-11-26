package com.DuAn1.techstore.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.DuAn1.techstore.Model.SanPham;
import com.DuAn1.techstore.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Activity_ChiTietSp extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView tvTen;
    private TextView tvGia;
    private TextView tvGiaCu;
    private TextView tvSoLuong;
    private TextView tvThongTin;
    private AppCompatButton btnMuaNgay;
    private AppCompatButton btnThemGioHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);
        Anhxa();
        ActionBar();
        getDl();
        btnMuaNgay.setOnClickListener(view -> MuaNgay());
        btnThemGioHang.setOnClickListener(view -> ThemGioHang());

    }

    private void MuaNgay() {
    }

    private void ThemGioHang() {
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Chi tiết sản phẩm");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @SuppressLint("SetTextI18n")
    private void getDl() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        SanPham sanPham = (SanPham) bundle.get("sanPham");
        Picasso.get().load(sanPham.getHinhAnh())
                .placeholder(R.drawable.dongho)
                .error(R.drawable.atvphone)
                .into(imageView);
        tvTen.setText(sanPham.getTenSanPham());
        DecimalFormat format = new DecimalFormat("###,###,###");
        tvGia.setText(format.format(sanPham.getGiaTien()) + "đ");
        tvGiaCu.setText(format.format(sanPham.getGiaCu()) + "đ");
        tvGiaCu.setPaintFlags(tvGiaCu.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvSoLuong.setText("Số lượng: " + sanPham.getSoLuongNhap());
        tvThongTin.setText(sanPham.getThongTinSanPham());
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.img);
        tvTen = findViewById(R.id.tvTen);
        tvGia = findViewById(R.id.tvGia);
        tvGiaCu = findViewById(R.id.tvGiaCu);
        tvSoLuong = findViewById(R.id.tvSoLuong);
        tvThongTin = findViewById(R.id.tvThongTin);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        btnThemGioHang = findViewById(R.id.btnThemGioHang);
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
        getMenuInflater().inflate(R.menu.cart, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}