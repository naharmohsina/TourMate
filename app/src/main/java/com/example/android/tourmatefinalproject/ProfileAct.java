package com.example.android.tourmatefinalproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.List;

public class ProfileAct extends AppCompatActivity implements View.OnClickListener {
    private ImageView web;
    private ImageView facebook,memory;
    //private ImageView insta;
    private ImageView mail;
    private FirebaseAuth firebaseAuth;
    //private GoogleMap mMap;
    private FusedLocationProviderClient client;
    private LocationRequest request;
    private LocationCallback callback;
    private static int REQUEST_CODE_FOR_LOCATION=1;
    private Geocoder geocoder;
    private String locationName;
    double lat;
    double lon;
    LatLngBounds bounds;



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LogInActivity.class));
        }
        web = (ImageView) findViewById(R.id.web_img);
        //insta=(ImageView)findViewById(R.id.insta_img);
        mail = (ImageView) findViewById(R.id.mail_img);
        facebook = (ImageView) findViewById(R.id.fb_img);
        memory=(ImageView)findViewById(R.id.memorable_img);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//         la = location.getLatitude();
//         lo = location.getLongitude();


        bounds = new LatLngBounds(new LatLng(23.720659, 90.468735),new LatLng(23.888483, 90.378766));
        client = LocationServices.getFusedLocationProviderClient(this);
        request = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(3000).setFastestInterval(1000);

        geocoder = new Geocoder(this);
        callback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    //latTV.setText(String.valueOf(lat));
                    //lonTV.setText(String.valueOf(lon));

                    try {
                        List<Address> addresses = geocoder.getFromLocation(lat,lon,1);
                        locationName = addresses.get(0).getLocality()+" ,"+ addresses.get(0).getSubLocality()+", "+ addresses.get(0).getPostalCode()+","+addresses.get(0).getFeatureName();
                        // addressTv.setText(locationName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},REQUEST_CODE_FOR_LOCATION);
            return;
        }
        client.requestLocationUpdates(request, callback, null);


       // mMap = googleMap;

        // Add a marker in Sydney and move the camera


        web.setOnClickListener(this);
        //insta.setOnClickListener(this);
        facebook.setOnClickListener(this);
        mail.setOnClickListener(this);
        memory.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.web_img){
//            Toast.makeText(ProfileAct.this,"Tour Clicked",Toast.LENGTH_SHORT
//            ).show();
           startActivity(new Intent(ProfileAct.this,TourActivity.class));
        }
        else if (view.getId() == R.id.fb_img){
            // e.g. if your URL is https://www.facebook.com/EXAMPLE_PAGE, you should put EXAMPLE_PAGE at the end of this URL, after the ?
//        Toast.makeText(ProfileAct.this,"Weather Clicked",Toast.LENGTH_SHORT
//            ).show();
            startActivity(new Intent(ProfileAct.this,WeatherForecast.class)
                    .putExtra("latitude",lat)
                    .putExtra("longitude",lon)
                    .putExtra("Address",locationName));
            Toast.makeText(ProfileAct.this,""+lat+"  "+lon,Toast.LENGTH_LONG).show();


        }
        else if (view.getId() == R.id.mail_img){
//            Toast.makeText(ProfileAct.this,"NearBy Clicked",Toast.LENGTH_SHORT
//            ).show();
            startActivity(new Intent(ProfileAct.this,NearbyActivity.class)
                    .putExtra("latitude",lat)
                    .putExtra("longitude",lon));

        }
        else if(view.getId()==R.id.memorable_img)
        {
//            Toast.makeText(ProfileAct.this,"Memorable Clicked",Toast.LENGTH_SHORT
//            ).show();
            startActivity(new Intent(ProfileAct.this,Memorable.class));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.logout)
        {
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LogInActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}