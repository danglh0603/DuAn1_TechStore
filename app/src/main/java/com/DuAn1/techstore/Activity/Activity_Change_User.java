package com.DuAn1.techstore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.DuAn1.techstore.R;

public class Activity_Change_User extends AppCompatActivity {
    ImageView img_back_tai_khoan;
    Button btn_doi_thong_tin, btn_doi_mat_khau;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);

        AnhXa();
        img_back_tai_khoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(this,SecondActivity.class);
//                Bundle bundle = new Bundle();
//// đóng gói kiểu dữ liệu String, Boolean
//                bundle.putString("key_1", "MainActivity greeted you with a HI");
//                bundle.putBoolean("key_2", true);
//// đóng gói bundle vào intent
//                intent.putExtras(bundle);
//// start SecondActivity
//                startActivity(intent);
                intent = new Intent(Activity_Change_User.this, ManChinhActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tai_khoan", "3");
                intent.putExtras(bundle);
//                ManChinhActivity.replaceFragments(FragmentDoiThongTin.class);
                startActivity(intent);
//                ((ManChinhActivity) getActivity()).popBackFragments();
            }
        });
        btn_doi_thong_tin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Activity_Change_User.this, Activity_Doi_Thong_Tin.class);
                startActivity(intent);
//                ((ManChinhActivity) getActivity()).replaceFragments(FragmentDoiThongTin.class);
            }
        });
        btn_doi_mat_khau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Activity_Change_User.this, Activity_Doi_Mat_Khau.class);
                startActivity(intent);
//                ((ManChinhActivity) getActivity()).replaceFragments(FragmentDoiMatKhau.class);
            }
        });
    }

    public void AnhXa() {
        img_back_tai_khoan = findViewById(R.id.img_back_tai_khoan);
        btn_doi_thong_tin = findViewById(R.id.btn_doi_thong_tin);
        btn_doi_mat_khau = findViewById(R.id.btn_doi_mat_khau);
    }
}