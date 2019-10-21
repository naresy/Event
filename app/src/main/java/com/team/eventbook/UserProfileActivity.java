package com.team.eventbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    TextView tab1, tab2, tab3;
    Button userprofileEdit;
    private ViewPager viewPager;
    public TabsAccessAdapter tabsAccessAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setTitle("");
        tab1 = findViewById(R.id.Tab1);
        tab2 = findViewById(R.id.Tab2);
        tab3 = findViewById(R.id.Tab3);
        userprofileEdit=findViewById(R.id.user_profile_edit);
        viewPager = findViewById(R.id.container);
        tabsAccessAdapter = new TabsAccessAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAccessAdapter);
        userprofileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this,UseraSettingActivity.class));
            }
        });
        ///for sliding view pager and tab click method
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab1.setBackgroundResource(R.drawable.background);
                tab2.setBackgroundResource(R.drawable.background);
                tab3.setBackgroundResource(R.drawable.background);

                if (position == 0) {
                    tab1.setBackgroundResource(R.drawable.line);
                }
                if (position == 1) {
                    tab2.setBackgroundResource(R.drawable.line);

                }
                if (position == 2) {
                    tab3.setBackgroundResource(R.drawable.line);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void tabClick(View view)
    {
        tab1.setBackgroundResource(R.drawable.background);
        tab2.setBackgroundResource(R.drawable.background);
        tab3.setBackgroundResource(R.drawable.background);
       if (view.getId()==R.id.Tab1)
       {
           viewPager.setCurrentItem(0);
           tab1.setBackgroundResource(R.drawable.line);
       }
       if (view.getId()==R.id.Tab2)
       {
           viewPager.setCurrentItem(1);
           tab2.setBackgroundResource(R.drawable.line);

       }
       if (view.getId()==R.id.Tab3)
       {
           tab3.setBackgroundResource(R.drawable.line);
           viewPager.setCurrentItem(2);
       }
    }
    //end here the tab click  method

}
