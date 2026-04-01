package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;
public class MainActivityDevelopers extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private SlideAdapter slideAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_developers);
        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        slideAdapter = new SlideAdapter(this);
        mSlideViewPager.setAdapter(slideAdapter);
    }
}
