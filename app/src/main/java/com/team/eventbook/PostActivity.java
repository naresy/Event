package com.team.eventbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class PostActivity extends AppCompatActivity implements   AdapterView.OnItemSelectedListener {
    String[] Categories = { "Education", "Business", "Musical", "Social", "Occasion"};
    static EditText datepicker,datepickerEdn,timepickerstart,timepickerTo,postTitle,PostDescription,fromdate_repeation,todate_repeation,from_time_repeation
            ,to_time_repeation;
   static TextView location,location_repeation;
  static EditText locationview,partner;
    RadioGroup postrepeation,postfee;
    Spinner postcategories;
    Button PostclickButton;
    TextView postusername;
    ImageView postuserimage;
    static ProgressDialog progressDialog;
    static String postURL="http://192.168.1.100/dummy/event_post.php";
    final Calendar myCalendar = Calendar.getInstance();
    private static final String TAG = "PostActivity";
    static String categoriesvalue;
    Dialog mDialog;
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().setTitle("");
        progressDialog=new ProgressDialog(this);
        datepicker = findViewById(R.id.post_date_from);
        datepickerEdn=findViewById(R.id.post_date_To);
        timepickerstart=findViewById(R.id.post_time_from);
        timepickerTo=findViewById(R.id.post_time_To);
        postTitle=findViewById(R.id.user_post_title);
        PostDescription=findViewById(R.id.post_description);
        postrepeation=findViewById(R.id.Post_repeatation);
        postfee=findViewById(R.id.Post_user_fee);
        location=findViewById(R.id.getlocation);
        locationview=findViewById(R.id.full_location);
        PostclickButton=findViewById(R.id.user_post_button);
        postusername=findViewById(R.id.post_user_name);
        postuserimage=findViewById(R.id.user_post_image);
        partner=findViewById(R.id.post_partner);
        postcategories=findViewById(R.id.post_Categories);
        fromdate_repeation=findViewById(R.id.post_date_from_repeation);
        todate_repeation=findViewById(R.id.post_date_To_repeatation);
        from_time_repeation=findViewById(R.id.post_time_from_repeation);
        to_time_repeation=findViewById(R.id.post_time_To_repeation);
        location_repeation=findViewById(R.id.getlocation_repeation);
        mDialog=new Dialog(this);
        postcategories.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postcategories.setAdapter(aa);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PostActivity.this, LocationActivity.class));

            }
        });
        PostclickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isfileEmpty(PostDescription)&&LoginActivity.isfileEmpty(postTitle)&&LoginActivity.
                        isfileEmpty(datepicker)&&LoginActivity.isfileEmpty(datepickerEdn)&&LoginActivity.
                        isfileEmpty(timepickerstart)&&LoginActivity.isfileEmpty(
                        timepickerTo)&&LoginActivity.isfileEmpty(locationview))
                {
                  PostRegistrartion();

                }

            }
        });
        //section for time and date picker
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
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel1();
            }


        };







        datepicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PostActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        datepickerEdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PostActivity.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    private void PostRegistrartion() {
        progressDialog.setTitle("Post Registration");
        progressDialog.setMessage("Please wait while Posting...?");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        final String Descriptionvalue=PostDescription.getText().toString();
        final String loactionavalue=locationview.getText().toString();
        final String PartnerValue=partner.getText().toString();
        final String datepickerstartvalue=datepicker.getText().toString();
        final String datepickerendvalue=datepickerEdn.getText().toString();
        final String timepickerstartvalue=timepickerstart.getText().toString();
        final String timepickerTovalue=timepickerTo.getText().toString();
        final String postTitilevalue=postTitle.getText().toString();
        RadioButton chected=findViewById(postrepeation.getCheckedRadioButtonId());
        final String repeatationvalue=chected.getText().toString();
        RadioButton check=findViewById(postfee.getCheckedRadioButtonId());

        final String postprice=check.getText().toString();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, postURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String Success = jsonObject.getString("success");
                            if (Success.equals("1")) {
                                Toast.makeText(PostActivity.this,"Post Registration Success...",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(PostActivity.this, MainActivity.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PostActivity.this,"Error!"+e,Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PostActivity.this,"Error!"+error,Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("description",Descriptionvalue);
                params.put("title",postTitilevalue);
                params.put("fromdate",datepickerstartvalue);
                params.put("todate",datepickerendvalue);
                params.put("fromtime",timepickerstartvalue);
                params.put("totime",timepickerTovalue);
                params.put("repeatation",repeatationvalue);
                params.put("venue",loactionavalue);
                params.put("partner",PartnerValue);
                params.put("price",postprice);
                params.put("categories",categoriesvalue);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which we need put here in text view
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datepicker.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateLabel1() {
        String myFormat = "MM/dd/yyyy"; //In which we need put here in text view
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datepickerEdn.setText(sdf.format(myCalendar.getTime()));
    }




    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new Timepickerforpost();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showTimePickerDialog1(View v) {
        DialogFragment newFragment = new TimepickerforpostTo();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    //Date picker section end
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),Categories[position] , Toast.LENGTH_LONG).show();
         categoriesvalue = postcategories.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

public void showpopup(View v)
{
    mDialog.setContentView(R.layout.layoutforpostfee);
    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

    mDialog.show();
}

public void showpopup1(View view)
{
    mDialog.setContentView(R.layout.layoutforrepeatation);
    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    mDialog.show();
}

}
