package com.DuAn1.techstore.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.DuAn1.techstore.Adapter.AdapterTabLayout;
import com.DuAn1.techstore.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ManChinhActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manchinh);
        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout);
        AdapterTabLayout adapterTabLayout = new AdapterTabLayout(ManChinhActivity.this);
        viewPager2.setAdapter(adapterTabLayout);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0: {
                    tab.setText("Trang chủ");
                    tab.setIcon(R.drawable.ic_baseline_person_24);// set icon
                    break;
                }
                case 1: {
                    tab.setText("Giỏ hàng");
                    tab.setIcon(R.drawable.ic_baseline_shopping_cart_24);
                    break;
                }
                case 2: {
                    tab.setText("Tài khoản");
                    tab.setIcon(R.drawable.ic_baseline_person_24);
                    break;
                }
            }
        }).attach();


    }
}
