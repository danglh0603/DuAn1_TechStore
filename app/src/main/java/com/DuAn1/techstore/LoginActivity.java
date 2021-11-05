package com.DuAn1.techstore;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText edUsername;
    private EditText edPassword;
    private Button btnLogin;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

//        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
//        String user = pref.getString("USERNAME", "");
//        String pass = pref.getString("PASSWORD", "");
//
//
//        edUserName.setText(user);
//        edPassword.setText(pass);


    }
}