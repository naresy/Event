package com.team.eventbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    static EditText Email,password,username;
    CheckBox term_and_condition;
    ImageButton user_signup;
    Button userlogin;
    ProgressDialog progressDialog;
    private static String URL_REG="http://192.168.1.100/dummy/user_register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog=new ProgressDialog(this);
        username=findViewById(R.id.Register_name);
        Email=findViewById(R.id.register_email);
        password=findViewById(R.id.Register_password);
        term_and_condition=findViewById(R.id.term_and_condition);
        user_signup=findViewById(R.id.Register_signup_imagebutton);
        userlogin=findViewById(R.id.Register_signin);
        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Registerfunction();
            }
        });

    }

    private void Registerfunction() {
        if(LoginActivity.isfileEmpty(username)&&LoginActivity.isfileEmpty(Email)&&LoginActivity.isfileEmpty(password)&&LoginActivity.isemailEmpty(Email))
        {
            progressDialog.setTitle("User Registration");
            progressDialog.setMessage("Please wait while Register...!");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            user_signup.setVisibility(View.INVISIBLE);
            final String name=this.username.getText().toString().trim();
            final String email=this.Email.getText().toString().trim();
            final String password=this.password.getText().toString().trim();
            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REG,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {


                                JSONObject jsonObject = new JSONObject(response);
                                String success=jsonObject.getString("success");
                                if(success.equals("1"))
                                {
                                    Toast.makeText(RegisterActivity.this,"register success!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this,UseraSettingActivity.class));


                                }
                            }
                            catch ( JSONException e)
                            {
                                e.printStackTrace();
                                Toast.makeText(RegisterActivity.this,"Error!",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                user_signup.setVisibility(View.VISIBLE);
                            }

                        }
                    },new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this,"Error!"+error.toString(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    user_signup.setVisibility(View.VISIBLE);


                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params=new HashMap<>();
                    params.put("name",name);
                    params.put("email",email);
                    params.put("password",password);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }

        }

}
