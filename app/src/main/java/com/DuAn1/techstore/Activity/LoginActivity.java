package com.DuAn1.techstore.Activity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.DuAn1.techstore.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
//    private EditText edUsername;
//    private EditText edPassword;
//    private CheckBox chkRemember;

//    private Button btnLogin;
//    private TextView tvRegister;


    private Button btnHuy;
    private Button btnThoat;
    private ImageView imageView;
    private TextView tvChaoMung;
    private TextView tvTechStore;
    private TextView tvDangNhap;
    private TextInputLayout textInputLayout1;
    private TextInputEditText edUsername;
    private TextInputLayout textInputLayout2;
    private TextInputEditText edPassword;
    private CheckBox chkRemember;
    private AppCompatButton btnLogin;
    private TextView textView4;
    private TextView tvResigter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //anh xa
        imageView = findViewById(R.id.imgLogo);
        tvChaoMung = findViewById(R.id.tvChaoMung);
        tvTechStore = findViewById(R.id.tvTechStore);
        tvDangNhap = findViewById(R.id.tvDangNhap);
        textInputLayout1 = findViewById(R.id.textInputLayout1);
        edUsername = findViewById(R.id.edUsername);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        edPassword = findViewById(R.id.edPassword);
        chkRemember = findViewById(R.id.chkRemember);
        btnLogin = findViewById(R.id.btnLogin);
        textView4 = findViewById(R.id.textView4);
        tvResigter = findViewById(R.id.tvResigter);
        animationLogo();


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

    private void animationLogo() {
        //set vị trí ban đầu
        imageView.setTranslationY(550);
        textView4.setTranslationY(1000);
        tvResigter.setTranslationY(1000);
        //constraintLayout.setTranslationY(-900);
        //ẩn
        tvChaoMung.setAlpha(0);
        tvTechStore.setAlpha(0);
        tvDangNhap.setAlpha(0);
        textInputLayout1.setAlpha(0);
        textInputLayout2.setAlpha(0);
        chkRemember.setAlpha(0);
        btnLogin.setAlpha(0);
        // animation
        imageView.animate().translationY(0).setDuration(1200).setStartDelay(300).start();
        tvDangNhap.animate().alpha(1).setDuration(1200).setStartDelay(500).start();
        tvChaoMung.animate().alpha(1).setDuration(1200).setStartDelay(700).start();
        tvTechStore.animate().alpha(1).setDuration(1200).setStartDelay(900).start();
        textInputLayout1.animate().alpha(1).setDuration(1200).setStartDelay(1100).start();
        textInputLayout2.animate().alpha(1).setDuration(1200).setStartDelay(1300).start();
        chkRemember.animate().alpha(1).setDuration(1200).setStartDelay(1500).start();
        btnLogin.animate().alpha(1).setDuration(1200).setStartDelay(1700).start();
        textView4.animate().translationY(0).setDuration(1200).setStartDelay(1900).start();
        textView4.animate().alpha(1).setDuration(1200).setStartDelay(1900).start();
        tvResigter.animate().translationY(0).setDuration(1200).setStartDelay(2100).start();
        tvResigter.animate().alpha(1).setDuration(1200).setStartDelay(2100).start();
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