package com.team.eventbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    static EditText Email, password;
    CheckBox remember_me;
    ImageButton userlogin;
    Button user_signup;
    TextView forgetpassword;
    static ProgressDialog progressDialog;
    private static String URL_login="http://192.168.1.100/dummy/user_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        Email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        remember_me = findViewById(R.id._login_checkbox);
        forgetpassword = findViewById(R.id.login_forgetpassword);
        userlogin = findViewById(R.id.signin_imaje_button);
        user_signup = findViewById(R.id.signup_button);
        user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // login input section
        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isfileEmpty(Email) && isfileEmpty(password) && isemailEmpty(Email)) {
                    progressDialog.setTitle("User Login");
                    progressDialog.setMessage("Please wait while login....!");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    String useremailvalue = Email.getText().toString();
                    String userpasswordvalue = password.getText().toString();
                    LoginFunction(useremailvalue,userpasswordvalue);

                }


            }
        });
    }

    private void LoginFunction(final String email,final String password) {
            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_login,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response);
                                String success=jsonObject.getString("success");
                                JSONArray jsonArray=jsonObject.getJSONArray("login");
                                if(success.equals("1"))
                                {
                                    for (int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject object=jsonArray.getJSONObject(i);
                                        String name=object.getString("name").trim();
                                        String email=object.getString("email").trim();
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    }
                                }

                            }catch (JSONException e)
                            {
                                Toast.makeText(LoginActivity.this,"Error"+e.toString(),Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this,"Error"+error.toString(),Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();


                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params=new HashMap<>();
                    params.put("email",email);
                    params.put("password",password);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);




        }



    //For validation
    public static boolean isfileEmpty(EditText view) {
        String value = view.getText().toString();
        if (value.length() > 0) {
            return true;
        } else
            view.setError("enter value");
        return false;
    }

    public static boolean isemailEmpty(EditText view) {
        if (isfileEmpty(view)) {
            String value = view.getText().toString();
            if (Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
                return true;
            } else {
                view.setError("invalid email");

            }

        }
        return false;
    }
    public static boolean isValidPassword(EditText view)
    {
      if (password.length()>6)
      {
          return true;
      }else {
          view.setError("please enter 6 digit password");
      }return false;
    }



}




