package com.DuAn1.techstore.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.DuAn1.techstore.Activity.ManChinhActivity;
import com.DuAn1.techstore.fragment.FragmentGioHang;
import com.DuAn1.techstore.fragment.FragmentManChinh;
import com.DuAn1.techstore.fragment.FragmentTaiKhoan;

public class AdapterTabLayout extends FragmentStateAdapter {
    public AdapterTabLayout(@NonNull ManChinhActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return new FragmentManChinh();
            }
            case 1: {
                return new FragmentGioHang();
            }
            case 2: {
                return new FragmentTaiKhoan();
            }
            default: {
                return new FragmentManChinh();
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
