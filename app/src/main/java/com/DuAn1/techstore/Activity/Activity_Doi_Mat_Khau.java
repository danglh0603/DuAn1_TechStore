package com.DuAn1.techstore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.DuAn1.techstore.R;

public class Activity_Doi_Mat_Khau extends AppCompatActivity {
    private ImageView img_change_pass_back_chang_user;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        AnhXa();
        img_change_pass_back_chang_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((ManChinhActivity) getActivity()).popBackFragments();
                intent = new Intent(Activity_Doi_Mat_Khau.this, Activity_Change_User.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa() {
        img_change_pass_back_chang_user = findViewById(R.id.img_change_pass_back_change_user);
    }
}