package com.team.eventbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class filterActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    tabaccessadapter tabaccessadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        viewPager=findViewById(R.id.container);
        tabLayout=findViewById(R.id.main_tab_bar_layout);
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setTitle("Filter");
        tabaccessadapter = new tabaccessadapter(getSupportFragmentManager());
        viewPager.setAdapter(tabaccessadapter);
    }
}
