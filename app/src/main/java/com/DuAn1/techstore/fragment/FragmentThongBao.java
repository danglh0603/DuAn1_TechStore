package com.DuAn1.techstore.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.DuAn1.techstore.R;

public class FragmentThongBao extends Fragment {
    private RecyclerView rcv;


    public FragmentThongBao() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongbao, null, false);
        AnhXa(view);
        return view;
    }

    private void AnhXa(View view) {
        rcv = view.findViewById(R.id.rcv);
    }

    private void getThongTinHoaDon() {

    }
}
