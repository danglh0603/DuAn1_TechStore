package com.DuAn1.techstore.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.DuAn1.techstore.Activity.ManChinhActivity;
import com.DuAn1.techstore.R;

public class FragmentChangeUser extends Fragment {
    ImageView img_back_tai_khoan;
    Button btn_doi_thong_tin, btn_doi_mat_khau;
    public FragmentChangeUser () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_user,null,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        img_back_tai_khoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ManChinhActivity) getActivity()).popBackFragments();
            }
        });
        btn_doi_thong_tin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ManChinhActivity) getActivity()).replaceFragments(FragmentDoiThongTin.class);
            }
        });
        btn_doi_mat_khau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ManChinhActivity) getActivity()).replaceFragments(FragmentDoiMatKhau.class);
            }
        });
    }

    public void AnhXa(View view) {
        img_back_tai_khoan = view.findViewById(R.id.img_back_tai_khoan);
        btn_doi_thong_tin = view.findViewById(R.id.btn_doi_thong_tin);
        btn_doi_mat_khau = view.findViewById(R.id.btn_doi_mat_khau);
    }
}
