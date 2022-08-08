package com.example.application2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;

public class GPSMainActivity extends AppCompatActivity {
private LocationManager lc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsmain);

        if(!lc.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("定位管理").setMessage("GPS尚未啟用.\n"+"請先開啟GPS?");
        }


    }
}