package com.DuAn1.techstore.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.DuAn1.techstore.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (checkLuuDangNhap("luuDangNhap")) {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashScreenActivity.this, ManChinhActivity.class);
                startActivity(intent);
                finish();
            }, 2000);
        } else {

            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }, 2000);
        }
    }

    private boolean checkLuuDangNhap(String key) {
        SharedPreferences sharedPreferences2 = getSharedPreferences("Luu_dangNhap", MODE_PRIVATE);
        return sharedPreferences2.getBoolean(key, false);
    }
}