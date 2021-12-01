package com.DuAn1.techstore.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.DuAn1.techstore.Activity.Activity_DienThoai;
import com.DuAn1.techstore.Activity.Activity_DongHo;
import com.DuAn1.techstore.Activity.Activity_Laptop;
import com.DuAn1.techstore.Activity.Activity_MayTinhBang;
import com.DuAn1.techstore.Activity.Activity_PhuKien;
import com.DuAn1.techstore.Adapter.AdapterSP_BanChay;
import com.DuAn1.techstore.Adapter.AdapterSlide;
import com.DuAn1.techstore.Adapter.GirdViewAdapterSp;
import com.DuAn1.techstore.DAO.Server;
import com.DuAn1.techstore.Model.DepthPageTransformer;
import com.DuAn1.techstore.Model.Photo;
import com.DuAn1.techstore.Model.SanPham;
import com.DuAn1.techstore.R;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;


public class FragmentManChinh extends Fragment {
    private final Context context;
    private GridView gridView;
    private SanPham sanPham;
    private ArrayList<SanPham> lstSp;
    private AdapterSP_BanChay adapterSP_banChay;
    private RecyclerView recyclerView;
    private ViewPager2 viewPager;
    private CircleIndicator3 viewCir;
    private ArrayList<Photo> mList;
    private final String[] ten = {
            "Điện thoại", "LapTop", "Đồng hồ", "Ipad", "Phụ kiện"
    };
    private final int[] hinh = {
            R.drawable.atvphone,
            R.drawable.laptop,
            R.drawable.dongho,
            R.drawable.maytinhbagn,
            R.drawable.headphones,
    };
    String url1 = "https://images.fpt.shop/unsafe/fit-in/800x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/10/21/637704543103517650_F_H1_800x300.png";
    String url2 = "https://images.fpt.shop/unsafe/fit-in/800x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/11/18/637728494005682482_F-H1_800x300.png";
    String url3 = "https://images.fpt.shop/unsafe/fit-in/800x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/11/9/637720541178079234_F-H1_800x300.png";
    String url4 = "https://images.fpt.shop/unsafe/fit-in/800x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/11/14/637724784941860214_F_H1_800x300.png";
    String url5 = "https://images.fpt.shop/unsafe/fit-in/800x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/11/19/637729280202093567_F-H1_800x300.png";
    String url6 = "https://images.fpt.shop/unsafe/fit-in/800x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/11/2/637714572252786896_F-H1_800x300.png";
    String url7 = "https://images.fpt.shop/unsafe/fit-in/800x300/filters:quality(90):fill(white)/fptshop.com.vn/Uploads/Originals/2021/8/31/637660487336798016_F_H1_800x300.png";


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == mList.size() - 1) {
                viewPager.setCurrentItem(0);
            } else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };

    public FragmentManChinh(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_man_chinh, null);
        AnhXa(view);
        GridTL();
        Slide();
        ActivityTL();
        getDlSp();


        return view;
    }

    private void AnhXa(View view) {
        gridView = view.findViewById(R.id.gridView);
        viewPager = view.findViewById(R.id.viewPager2);
        viewCir = view.findViewById(R.id.cir3);
        recyclerView = view.findViewById(R.id.rcvSPBanChay);

        //
        lstSp = new ArrayList<>();
        //
        adapterSP_banChay = new AdapterSP_BanChay(getContext(), lstSp);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterSP_banChay);
    }


    private ArrayList<Photo> getListPhoto() {
        ArrayList<Photo> list = new ArrayList<>();
        list.add(new Photo(url1));
        list.add(new Photo(url2));
        list.add(new Photo(url3));
        list.add(new Photo(url4));
        list.add(new Photo(url5));
        list.add(new Photo(url6));
        list.add(new Photo(url7));
        return list;
    }

    private void Slide() {
        mList = getListPhoto();
        AdapterSlide adapterSlide = new AdapterSlide(mList);
        viewPager.setAdapter(adapterSlide);

        viewCir.setViewPager(viewPager);

        viewPager.setPageTransformer(new DepthPageTransformer());
        // auto chạy sau 5s
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 5000);
            }
        });
    }


    private void getDlSp() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);//khai bao context
        //JsonArrayRequest(duongdan,neuThanhCong,neuThatBai);
        //tao request
        //xu ly khi thanh cong
        //xu ly khi that bai
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.spMoiNhat, response -> {
            if (response != null) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);//lay doi tuong thu i
                        sanPham = new SanPham();
                        sanPham.setMaSanPham(jsonObject.getInt("maSP"));
                        sanPham.setMaLoai(jsonObject.getInt("maLoai"));
                        sanPham.setTenSanPham(jsonObject.getString("tenSP"));
                        sanPham.setSoLuongNhap(jsonObject.getInt("soLuongNhap"));
                        sanPham.setHinhAnh(jsonObject.getString("hinhAnh"));
                        sanPham.setGiaTien(jsonObject.getInt("giaTien"));
                        sanPham.setGiaCu(jsonObject.getInt("giaCu"));
                        sanPham.setNgayNhap(jsonObject.getString("ngayNhap"));
                        sanPham.setThongTinSanPham(jsonObject.getString("thongTinSP"));
                        lstSp.add(sanPham);
                        adapterSP_banChay.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }, error -> {
            Toast.makeText(context, "Lỗi mạng!", Toast.LENGTH_SHORT).show();

        });
        requestQueue.add(jsonArrayRequest);//add request vao xu ly
    }

    private void GridTL() {
        GirdViewAdapterSp girdViewAdapterSp = new GirdViewAdapterSp(getActivity(), ten, hinh);
        gridView.setAdapter(girdViewAdapterSp);
    }

    private void ActivityTL() {
        gridView.setOnItemClickListener((adapterView, view, i, l) -> {
            switch (i) {
                case 0: {
                    Intent in = new Intent(getActivity(), Activity_DienThoai.class);
                    startActivity(in);
                    break;
                }
                case 1: {
                    Intent in = new Intent(getActivity(), Activity_Laptop.class);
                    startActivity(in);
                    break;
                }
                case 2: {
                    Intent in = new Intent(getActivity(), Activity_DongHo.class);
                    startActivity(in);
                    break;
                }
                case 3: {
                    Intent in = new Intent(getActivity(), Activity_MayTinhBang.class);
                    startActivity(in);
                    break;
                }
                case 4: {
                    Intent in = new Intent(getActivity(), Activity_PhuKien.class);
                    startActivity(in);
                    break;
                }
            }

        });
    }


    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapterSP_banChay == null) {
            adapterSP_banChay.fixMemoryLeak();
        }
    }
}