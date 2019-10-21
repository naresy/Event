package com.team.eventbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UseraSettingActivity extends AppCompatActivity {
    EditText datepicker,firstname,lastname,address,phonenumber,Biography;
    ImageView profileImaje;
    Button UpdateInformation;
    final Calendar myCalendar = Calendar.getInstance();
    Button btnGPSShowLocation;
    Button btnShowAddress;
    RadioGroup gender;
    TextView tvAddress;
    private  static Bitmap bitmap;
    AppLocationService appLocationService;
    ProgressDialog progressDialog;
    private static String ProURL="http://192.168.1.100/dummy/user_profile.php";
    private static final String TAG=UseraSettingActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usera_setting);
        getSupportActionBar().setTitle("");
        progressDialog=new ProgressDialog(this);
        firstname=findViewById(R.id.user_firstname);
        lastname=findViewById(R.id.user_lastname);
        address=findViewById(R.id.user_address);
        phonenumber=findViewById(R.id.user_phone);
        gender =findViewById(R.id.user_gender);
        Biography=findViewById(R.id.user_bio);
        profileImaje=findViewById(R.id.user_profile_image);
        UpdateInformation=findViewById(R.id.user_update_information);
        UpdateInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isfileEmpty(firstname)&&LoginActivity.isfileEmpty(lastname)&&LoginActivity.isfileEmpty(address)&&LoginActivity.isfileEmpty(phonenumber))
                {
                    RegisterUserInformation();

                }
                else {
                    Toast.makeText(UseraSettingActivity.this,"error",Toast.LENGTH_SHORT).show();
                }

            }
        });
        profileImaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cooseimagefromfile();
            }
        });


//This section for date picker
        datepicker = findViewById(R.id.user_Dateofbirth);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        datepicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UseraSettingActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //This section for live LocationActivity.java
        tvAddress = (TextView) findViewById(R.id.user_location_view);
        appLocationService = new AppLocationService(
                UseraSettingActivity.this);

        btnGPSShowLocation = (Button) findViewById(R.id.user_valid_location);
        btnGPSShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Location gpsLocation = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);
                if (gpsLocation != null) {
                    double latitude = gpsLocation.getLatitude();
                    double longitude = gpsLocation.getLongitude();
                    String result = "Latitude: " + gpsLocation.getLatitude() +
                            " Longitude: " + gpsLocation.getLongitude();
                    tvAddress.setText(result);
                } else {
                    showSettingsAlert();
                }
            }
        });

        btnShowAddress = (Button) findViewById(R.id.user_valid_location);
        btnShowAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Location location = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);

                //you can hard-code the lat & long if you have issues with getting it
                //remove the below if-condition and use the following couple of lines
                //double latitude = 37.422005;
                //double longitude = -122.084095

                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                } else {
                    showSettingsAlert();
                }

            }
        });


    }

    private void RegisterUserInformation() {

        progressDialog.setTitle("profile");
        progressDialog.setMessage("please wait while setup profile..!");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        Toast.makeText(UseraSettingActivity.this,"User Profile Setup",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UseraSettingActivity.this,MainActivity.class));
        final String firstnamevalue=firstname.getText().toString();
        final String lastnamevalue=lastname.getText().toString().trim();
        final String addressvalue=address.getText().toString().trim();
        final String phonenumbervalue=phonenumber.getText().toString().trim();
        final String Biographyvalue=Biography.getText().toString().trim();
        final String Dateofbirth=datepicker.getText().toString().trim();
        final String location=tvAddress.getText().toString().trim();
        RadioButton checkbtn=findViewById(gender.getCheckedRadioButtonId());
        final String gendervalue=checkbtn.getText().toString().trim();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ProURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {


                            JSONObject jsonObject=new JSONObject(response);
                            String success=jsonObject.getString("status");
//                            if (success.equals("1"))
//                            {
//                                Toast.makeText(UseraSettingActivity.this,"User Profile Setup",Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(UseraSettingActivity.this,UserProfileActivity.class));
//                            }
                           // Toast.makeText(UseraSettingActivity.this,"Success",Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(UseraSettingActivity.this,UserProfileActivity.class));

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getContentTransitionManager();
                Toast.makeText(UseraSettingActivity.this,"error"+error,Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();


            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("firstname",firstnamevalue);
                params.put("lastname",lastnamevalue);
                params.put("dob",Dateofbirth);
                params.put("address",addressvalue);
                params.put("phone",phonenumbervalue);
                params.put("gender",gendervalue);
                params.put("bio",Biographyvalue);
                params.put("location",location);
                params.put("image1",getStringImage(bitmap));

                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);




    }

    private void cooseimagefromfile() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select picture"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&& resultCode==RESULT_OK && data !=null && data.getData()!=null)
        {
            Uri filepath=data.getData();
            try
            {
             bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
             profileImaje.setImageBitmap(bitmap);
            }catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }


    public static String getStringImage(Bitmap bitmap1)
    {
        ByteArrayOutputStream byteArrayOutputStream =new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[]imagebutearry=byteArrayOutputStream.toByteArray();
        String encodeImage= Base64.encodeToString(imagebutearry,Base64.DEFAULT);
        return encodeImage;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                UseraSettingActivity.this);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        UseraSettingActivity.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            tvAddress.setText(locationAddress);
        }
    }

        //Date picker class
        private void updateLabel() {
            String myFormat = "MM/dd/yyyy"; //In which we need put here in text view
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            datepicker.setText(sdf.format(myCalendar.getTime()));
        }//Date picker section end
    }

