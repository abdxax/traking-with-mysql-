package com.example.abdulrahman.tracking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
final static int req=45;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Check();
    }

    public void Check(){
  if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
      requestPermissions(new String[]{
              Manifest.permission.ACCESS_FINE_LOCATION
      },req);
  }
  getLocation();
    }

    public void getLocation (){
        LocationManager manager=(LocationManager) getSystemService(LOCATION_SERVICE);
        MyLocation myLocation=new MyLocation(this);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,10,myLocation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case req:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }
        }
    }
}
