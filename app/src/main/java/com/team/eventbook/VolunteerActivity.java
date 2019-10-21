package com.team.eventbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class VolunteerActivity extends AppCompatActivity {
    EditText fullname,address,email,phone;
    RadioGroup Gender;
    Button  requestbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        fullname=findViewById(R.id.volunteer_username);
        address=findViewById(R.id.volunteer_address);
        email=findViewById(R.id.volunteer_email);
        phone=findViewById(R.id.volunteer_phone);
        requestbutton=findViewById(R.id.volunteer_request);
        requestbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isfileEmpty(fullname)&&LoginActivity.isfileEmpty(address)&&LoginActivity.isfileEmpty(email)&&LoginActivity.isfileEmpty(phone)&&LoginActivity.isemailEmpty(email))
                {
                    String fullnamevalue=fullname.getText().toString();
                    String addressvalue=address.getText().toString();
                    String emailvalue=email.getText().toString();
                    String phonevalue=phone.getText().toString();

                }
            }
        });

    }
}
