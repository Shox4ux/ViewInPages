package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.Listereners.OnFetchDataListener;
import com.example.Manager.RetrofitManager;
import com.example.Model.List;
import com.example.Model.Root;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    RetrofitManager manager;
//    Handler slideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new RetrofitManager(getApplicationContext());
        manager.getAllSections(listener);

    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(Root root, String message) {
            showMessage(message);
            setViewPager(root);
        }

        @Override
        public void onError(String message) {
            showMessage(message);
        }
    };

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }


    private void setViewPager(Root root){
        viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new SlideAdapter(root.list, viewPager2,getApplicationContext()));
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                slideHandler.removeCallbacks(slideRunnable);
//                slideHandler.postDelayed(slideRunnable,2500);
//            }
//        });

    }
//    private final Runnable slideRunnable = new Runnable() {
//        @Override
//        public void run() {
//            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
//        }
//    };
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        slideHandler.removeCallbacks(slideRunnable);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        slideHandler.postDelayed(slideRunnable,1000);
//    }
}