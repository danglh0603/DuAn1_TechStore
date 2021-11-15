package com.DuAn1.techstore.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.DuAn1.techstore.Adapter.GirdViewAdapterSp;
import com.DuAn1.techstore.R;


public class FragmentManChinh extends Fragment {

    GridView gridView;
    String[] ten = {
            "Dien Thoai", "LapTop", "Dong Ho", "Ipad", "Phu Kien"
    };
    int[] hinh = {
            R.drawable.atvphone,
            R.drawable.laptop,
            R.drawable.dongho,
            R.drawable.maytinhbagn,
            R.drawable.headphones,
    };

    public FragmentManChinh() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_man_chinh, null);

        gridView = view.findViewById(R.id.grid_sp);
        GirdViewAdapterSp girdViewAdapterSp = new GirdViewAdapterSp(getActivity(), ten, hinh);
        gridView.setAdapter(girdViewAdapterSp);
        return view;
    }
}