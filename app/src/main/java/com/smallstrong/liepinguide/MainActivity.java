package com.smallstrong.liepinguide;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView iv1, iv2, iv3, iv4;
    ViewPager viewPager;
    TextView tvGo;
    ArrayList<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        findById();
        initData();
        addListener();
    }

    private void addListener() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //positionOffset 向左滑   0 - 1 positionOffsetPixels 向左滑0 - 屏幕像素
                //positionOffset 向右滑   1 - 0 positionOffsetPixels 向左滑屏幕像素 - 0
                Log.d("onPageScrolled", "position===" + position + "         positionOffset" + positionOffset + "         positionOffsetPixels" + positionOffsetPixels);
                if (position == 0) {
                    iv1.setAlpha(1 - positionOffset);
                    iv2.setAlpha(positionOffset);
                } else if (position == 1 && positionOffsetPixels != 0) {
                    iv2.setAlpha(1 - positionOffset);
                    iv3.setAlpha(positionOffset);
                } else if (position == 2 && positionOffsetPixels != 0) {
                    iv3.setAlpha(1 - positionOffset);
                    iv4.setAlpha(positionOffset);
                } else {
                }
            }

            @Override
            public void onPageSelected(int position) {
                //0 1 2 3
                Log.d("onPageSelected", "position===" + position);
                if (position == 3) {
                    tvGo.setVisibility(View.VISIBLE);
                } else {
                    tvGo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("PageScrollStateChanged", "state===" + state);
            }
        });
    }

    private void findById() {
        iv1 = (ImageView) findViewById(R.id.iv_01);
        iv2 = (ImageView) findViewById(R.id.iv_02);
        iv3 = (ImageView) findViewById(R.id.iv_03);
        iv4 = (ImageView) findViewById(R.id.iv_04);
        tvGo = (TextView) findViewById(R.id.tv_go);
        viewPager = (ViewPager) findViewById(R.id.vp_guide);
    }

    private void initData() {
        ImageView ivText1 = new ImageView(this);
        ImageView ivText2 = new ImageView(this);
        ImageView ivText3 = new ImageView(this);
        ImageView ivText4 = new ImageView(this);
        ivText1.setImageResource(R.mipmap.guid1_text);
        ivText2.setImageResource(R.mipmap.guid2_text);
        ivText3.setImageResource(R.mipmap.guid3_text);
        ivText4.setImageResource(R.mipmap.guid4_text);
        views.add(ivText1);
        views.add(ivText2);
        views.add(ivText3);
        views.add(ivText4);
        viewPager.setAdapter(new ViewPaperAdapter(views));
    }

    public class ViewPaperAdapter extends PagerAdapter {
        private ArrayList<View> views;

        public ViewPaperAdapter(ArrayList<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            } else
                return 0;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(views.get(position), 0);
            return views.get(position);
        }

    }
}
