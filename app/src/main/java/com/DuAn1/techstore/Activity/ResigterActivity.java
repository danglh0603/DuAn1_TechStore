package com.DuAn1.techstore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.DuAn1.techstore.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ResigterActivity extends AppCompatActivity {
    private TextView tvDangKi;
    private TextInputLayout textInputLayout1;
    private TextInputEditText edTenDangNhap;
    private TextInputLayout textInputLayout2;
    private TextInputEditText edHoTen;
    private TextInputLayout textInputLayout3;
    private TextInputEditText edNamSinh;
    private TextInputLayout textInputLayout4;
    private TextInputEditText edSoDienThoai;
    private TextInputLayout textInputLayout5;
    private TextInputEditText edDiaChi;
    private TextInputLayout textInputLayout6;
    private TextInputEditText edMatKhau;
    private TextInputLayout textInputLayout7;
    private TextInputEditText edReMatKhau;
    private Button btnResigter;
    private TextView tvDangNhap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        Anhxa();
        Animation();
        // chuyen sang man dang nhap
        tvDangNhap.setOnClickListener(view -> DangNhap());
    }

    private void DangNhap() {
        Intent intent = new Intent(ResigterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void Anhxa() {
        tvDangKi = findViewById(R.id.tvDangKi);
        textInputLayout1 = findViewById(R.id.textInputLayout1);
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        edHoTen = findViewById(R.id.edHoTen);
        textInputLayout3 = findViewById(R.id.textInputLayout3);
        edNamSinh = findViewById(R.id.edNamSinh);
        textInputLayout4 = findViewById(R.id.textInputLayout4);
        edSoDienThoai = findViewById(R.id.edSoDienThoai);
        textInputLayout5 = findViewById(R.id.textInputLayout5);
        edDiaChi = findViewById(R.id.edDiaChi);
        textInputLayout6 = findViewById(R.id.textInputLayout6);
        edMatKhau = findViewById(R.id.edMatKhau);
        textInputLayout7 = findViewById(R.id.textInputLayout7);
        edReMatKhau = findViewById(R.id.edReMatKhau);
        btnResigter = findViewById(R.id.btnReigter);
        tvDangNhap = findViewById(R.id.tvDangNhap);
    }

    private void Animation() {
        tvDangKi.setTranslationX(1000);
        textInputLayout1.setTranslationX(1000);
        textInputLayout2.setTranslationX(1000);
        textInputLayout3.setTranslationX(1000);
        textInputLayout4.setTranslationX(1000);
        textInputLayout5.setTranslationX(1000);
        textInputLayout6.setTranslationX(1000);
        textInputLayout7.setTranslationX(1000);
        btnResigter.setTranslationX(1000);
        tvDangNhap.setTranslationX(1000);

        //
        tvDangKi.animate().translationX(0).setDuration(900).start();
        textInputLayout1.animate().translationX(0).setDuration(900).setStartDelay(200).start();
        textInputLayout2.animate().translationX(0).setDuration(900).setStartDelay(400).start();
        textInputLayout3.animate().translationX(0).setDuration(900).setStartDelay(600).start();
        textInputLayout4.animate().translationX(0).setDuration(900).setStartDelay(800).start();
        textInputLayout5.animate().translationX(0).setDuration(900).setStartDelay(1000).start();
        textInputLayout6.animate().translationX(0).setDuration(900).setStartDelay(1200).start();
        textInputLayout7.animate().translationX(0).setDuration(900).setStartDelay(1400).start();
        btnResigter.animate().translationX(0).setDuration(900).setStartDelay(1600).start();
        tvDangNhap.animate().translationX(0).setDuration(900).setStartDelay(1800).start();

    }
}
