package com.DuAn1.techstore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Manhome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhome);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Manhome.this, Manhome.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}

