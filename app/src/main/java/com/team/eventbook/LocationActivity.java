package com.team.eventbook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static AutoCompleteTextView locationSearch;
    static   LatLng latLng;
    Button google;
    String[] arr = { "kathmandu, Aalapot",
            "BandBhanjyang,kathmandu",
            "Bajrayogini,kathmandu",
            "Balambu,kathmandu",
            "Baneshwor,kathmandu",
            "Baluwa,kathmandu",
            "Bhadrabas,kathmandu",
            "Bhimdhunga,kathmandu",
            "Budanilkantha,kathmandu",
            "Chalnakhel,kathmandu",
            "ChapaliBhadrakali,kathmandu",
            "Chhaimale,kathmandu",
            "ChobharNepal,kathmandu",
            "ChouketarDahachok,kathmandu",
            " Chunikhel,kathmandu",
            "Daanchhi,kathmandu",
            " Daxinkali,kathmandu",
            "Dhapasi,kathmandu",
            "Dharmasthali,kathmandu",
            "Futung,kathmandu",
            "Gapalphedi,kathmandu",
            "Gokarna,kathmandu",
            "Goldhunga,kathmandu",
            "Gonggabu,kathmandu",
            "Gothatar,kathmandu",
            "IchankhuNarayan,kathmandu",
            " Indrayani,kathmandu",
            "JhorMahankal,kathmandu",
            "Jitpurphedi,kathmandu",
            "Jorpati,kathmandu",
            "Kabhresthali,kathmandu",
            " Kapan,kathmandu",
            "KhadkaBhadrakali,kathmandu",
            "KirtipurChitubihar,kathmandu",
            "Koteswor,kathmandu",
            "Lapsiphedi,kathmandu",
            "Machhegaun,kathmandu",
            "Mahadevsthan3,kathmandu",
            "Mahankal,kathmandu",
            "Manmaiju,kathmandu",
            "Matatirtha,kathmandu",
            "Mulpani,kathmandu",
            "Naglebhare,kathmandu",
            "NaikapNayaBhanjyang,kathmandu",
            "NaikapPuranoBhanjyang,kathmandu",
            "Nayapati,kathmandu",
            "Pukhulachhi,kathmandu",
            "Ramkot,kathmandu",
            "Sangla,kathmandu",
            "Satungal,kathmandu",
            "Seuchatar,kathmandu",
            "Sheshnarayan,kathmandu",
            "Sitapaila,kathmandu",
            "Sokhek,kathmandu",
            "Sundarijal,kathmandu",
            " Suntol,kathmandu",
            " Talkududechour,kathmandu",
            "Thankot,kathmandu",
            "Tinthana,kathmandu",
            "TokhaChandeswori,kathmandu",
            "TokhaSarswoti,kathmandu","Eastern College of Engineering (EASCOLL), Biratnagar",
            "Purbanchal University School of Engineering & Technology (PUSET),Biratnagar",
            "Kantipur Engineering College, TU",
            "Himalaya College of Engineering, TU",
            "Advanced College of Engineering and Management, TU",
            "Nepal Engineering College, PU",
            "Kathford Engineering College",
            "Gandaki College of Engineering and Science",
            "Kathmandu Engineering College",
            "Khwopa Engineering College",
            "Lumbini Engineering College",
            "Oxford college of engineering and management",
            "Pashchimanchal Campus",
            "Pokhara Engineering College",
            "Pulchowk campus",
            "Aryan School of Engineering",
            "Purwanchal Campus, Dharan",
            "Thapathali campus",
            "Universal Engineering and Science College",
            "National college of Engineering.Talchikhel,Lalitpur",
            "Kantipur International college, Lubhu, Lalitpur",
            "Nepal collge of information technology"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationSearch =  findViewById(R.id.editText);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, arr);
        locationSearch.setThreshold(2);
        locationSearch.setAdapter(adapter);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        google=findViewById(R.id.goople_type);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onMapSearch(View view) {

        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
          latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));
            String latitude=latLng.toString();
            PostActivity.locationview.setText(latitude+"\n"+"address:"+(location));


        }
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }

        });
        enablelocation();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void enablelocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        enablelocation();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(27.746974, 85.301582);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Kathmandu, Nepal"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}