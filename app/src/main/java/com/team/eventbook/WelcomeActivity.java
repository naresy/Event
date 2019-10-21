package com.team.eventbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    ImageView welcomeimageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeimageView = findViewById(R.id.welcome_logo);
        welcomeimageView.setAlpha(0F);
        welcomeimageView.setVisibility(View.VISIBLE);
        welcomeimageView.animate().alpha(1).setDuration(1500);
        Handler handler=new Handler(
        );
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }

        };
        handler.postDelayed(runnable,2000);

    }
}
