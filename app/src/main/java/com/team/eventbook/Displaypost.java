package com.team.eventbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Displaypost extends AppCompatActivity {
    TextView eventselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_home_view);
        eventselect=findViewById(R.id.Postview_with);
        eventselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Displaypost.this,MapsActivity.class));
            }
        });
    }
}
