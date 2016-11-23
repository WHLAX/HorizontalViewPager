package com.bawei.test;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.test.fragment.Myfragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private int widths;
    private HorizontalScrollView hs;
    private ArrayList<Fragment> flist = new ArrayList<Fragment>();
    private ViewPager vp;
    private RadioGroup rg;
    private String[] url = new String[] {"http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17",
            "http://m.yunifang.com/yunifang/mobile/goods/getall?random=39986&encode=2092d7eb33e8ea0a7a2113f2d9886c90&category_id=17"
           };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       vp.setOffscreenPageLimit(5);
        vp = (ViewPager) findViewById(R.id.vp);
        rg = (RadioGroup) findViewById(R.id.rg);
        hs = (HorizontalScrollView) findViewById(R.id.hs);
        // 调节适配屏幕大小
        widths = getResources().getDisplayMetrics().widthPixels;
        // 默认选择第一个视图
        rg.getChildAt(0).setSelected(true);
        vp.setCurrentItem(0);
        changeview(0);
        viewColor(0);
        buttinto();
        // fragment
        tofragment();
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                vp.setCurrentItem(arg0);
                changeview(arg0);
                viewColor(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        vp.setAdapter(new Myadapter(getSupportFragmentManager()));
    }
    private void tofragment() {
        for (int i = 0; i < rg.getChildCount(); i++) {
            Myfragment mf = new Myfragment();
            flist.add(mf);
        }
    }
    private void buttinto() {
        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton but = (RadioButton) rg.getChildAt(i);
            final int pos = i;
            but.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    vp.setCurrentItem(pos);
                    changeview(pos);
                    viewColor(pos);
                }
            });
        }
    }

    protected void viewColor(int pos) {
        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton but = (RadioButton) rg.getChildAt(i);
            if (pos == i) {
                but.setTextColor(Color.RED);
            } else {
                but.setTextColor(Color.GRAY);
            }
        }
    }

    protected void changeview(int pos) {
        int count = rg.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = rg.getChildAt(i);
            child.setSelected(i == pos);
        }
        View tabview = rg.getChildAt(pos);
        int left = tabview.getLeft();
        int width = tabview.getMeasuredWidth();
        int tox = left + width / 2 - widths / 2;
        hs.smoothScrollTo(tox, 0);
    }

    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            Fragment fragment = flist.get(arg0);
            Bundle bundle = new Bundle();
            bundle.putString("urls", url[arg0]);
            fragment.setArguments(bundle);
            return fragment;
        }

        public int getCount() {
            return flist.size();
        }
    }
}
