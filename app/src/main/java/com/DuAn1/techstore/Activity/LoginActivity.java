package com.DuAn1.techstore.Activity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.DuAn1.techstore.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername;
    private EditText edPassword;
    private CheckBox chkRemember;
    private Button btnHuy;
    private Button btnThoat;
    private Button btnLogin;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //anh xa
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        SharedPreferences sharedPreferences = getSharedPreferences("Accout_file", MODE_PRIVATE);
        edUsername.setText(sharedPreferences.getString("USER", ""));
        edPassword.setText(sharedPreferences.getString("PASS", ""));
        chkRemember.setChecked(sharedPreferences.getBoolean("REMEMBER", false));
    }

    // nho tk, mk
    private void rememberAccount(String user, String pass, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("Accout_file", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("USER", user);
            editor.putString("PASS", pass);
            editor.putBoolean("REMEMBER", true);
        }
        editor.apply();
    }

    // dialog thoat
    public void Exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.dialog_thoat, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        alertDialog.show();

        btnHuy = view.findViewById(R.id.btn_dialog_Huy);
        btnThoat = view.findViewById(R.id.btn_dialog_Thoat);

        btnThoat.setOnClickListener(v -> {
            alertDialog.dismiss();
            System.exit(0);

        });

        btnHuy.setOnClickListener(v -> alertDialog.dismiss());
    }

    @Override
    public void onBackPressed() {
        Exit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}