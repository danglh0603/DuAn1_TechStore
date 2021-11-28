package com.DuAn1.techstore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.DuAn1.techstore.Activity.Activity_DoiMatKhau;
import com.DuAn1.techstore.Activity.Activity_DoiThongTin;
import com.DuAn1.techstore.R;

public class FragmentTaiKhoan extends Fragment {
    public FragmentTaiKhoan() {
    }

    Button doitt,doimk,dx;
    ImageView anhuser;
    TextView tenuser,gmailuser,sldamua,textdamua,slgiohang,textgiohang;
    ImageButton phanthem;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan,null,false);

        doitt = view.findViewById(R.id.btn_doitt);
        doimk = view.findViewById(R.id.btn_dmk);
        anhuser = view.findViewById(R.id.anhuser);
        tenuser = view.findViewById(R.id.tenuser);
        gmailuser = view.findViewById(R.id.gmailuser);
        phanthem = view.findViewById(R.id.phanthem);
        sldamua = view.findViewById(R.id.sldamua);
        textdamua = view.findViewById(R.id.textdamua);
        textgiohang = view.findViewById(R.id.textgiohang);
        slgiohang = view.findViewById(R.id.slgiohang);


        doitt.setVisibility(View.GONE);
        doimk.setVisibility(View.GONE);

        phanthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                anhuser.setVisibility(View.GONE);
                tenuser.setVisibility(View.GONE);
                gmailuser.setVisibility(View.GONE);
                sldamua.setVisibility(View.GONE);
                textdamua.setVisibility(View.GONE);
                textgiohang.setVisibility(View.GONE);
                slgiohang.setVisibility(View.GONE);
                phanthem.setVisibility(View.GONE);

                doimk.setVisibility(View.VISIBLE);
                doitt.setVisibility(View.VISIBLE);


            }
        });

        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_DoiMatKhau.class);
                getContext().startActivity(intent);
            }
        });

        doitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), Activity_DoiThongTin.class);
                startActivity(intent1);
            }
        });

        return view;
    }
}
