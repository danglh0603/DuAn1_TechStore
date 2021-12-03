package com.DuAn1.techstore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.DuAn1.techstore.R;

public class Activity_Doi_Thong_Tin extends AppCompatActivity {
    private ImageView img_change_info_back_change_user;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_thong_tin);
        AnhXa();
        img_change_info_back_change_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((ManChinhActivity) getActivity()).popBackFragments();
                intent = new Intent(Activity_Doi_Thong_Tin.this, Activity_Change_User.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa() {
        img_change_info_back_change_user = findViewById(R.id.img_change_info_back_change_user);
    }
}