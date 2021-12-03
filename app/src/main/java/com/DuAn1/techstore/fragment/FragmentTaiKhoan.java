package com.DuAn1.techstore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.DuAn1.techstore.Activity.Activity_Change_User;
import com.DuAn1.techstore.Activity.ManChinhActivity;
import com.DuAn1.techstore.R;

public class FragmentTaiKhoan extends Fragment {
    ImageView img_chang_user;
    Intent intent;
    public FragmentTaiKhoan() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan, null, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa(view);
        img_chang_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent((ManChinhActivity)getActivity(), Activity_Change_User.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa(View view) {
        img_chang_user = view.findViewById(R.id.img_change_user);
    }
}
