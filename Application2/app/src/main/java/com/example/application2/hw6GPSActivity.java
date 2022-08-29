package com.example.application2;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class hw6GPSActivity extends AppCompatActivity implements LocationListener {
    private TextView textview_gps_information;
    private LocationManager lc;
    private Location currentLocation;
    private Button buttonSetGPS,buttonGetGPS,buttonShowMap;
    private String bestProvider;

    private  static  final  int PERMISSION_REQUSET_GPS=101;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsmain);

        textview_gps_information=findViewById(R.id.textview_gps_information);
        buttonSetGPS=findViewById(R.id.buttonSetGPS);
        buttonGetGPS=findViewById(R.id.buttonGetGPS);
        buttonShowMap=findViewById(R.id.buttonShowMap);
        lc= (LocationManager) getSystemService(LOCATION_SERVICE);

        if(!lc.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("GPS定位管理設定").setMessage("GPS尚未啟用\n請先開啟GPS才能接收位置資料");
            builder.setPositiveButton("確定",null);
            builder.create();
            builder.show();
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUSET_GPS);
            }
        }
        bestProvider=lc.getBestProvider(new Criteria(),true);
        lc.requestLocationUpdates(bestProvider,1000,1.0f, hw6GPSActivity.this);


        buttonSetGPS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        });
        buttonGetGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat,lng;
                String outputString;
                bestProvider=lc.getBestProvider(new Criteria(),true);
                try
                {
//                    currentLocation= lc.getLastKnownLocation(bestProvider);
                    if (currentLocation !=null)
                    {
                        lat=currentLocation.getLatitude();
                        lng=currentLocation.getLongitude();
                        outputString="目前 GPS 資訊 : 手動更新成功，定位資訊提供者 ＃"+bestProvider;
                        textview_gps_information.setText(outputString+"\n緯度資料: "+lat+"\n經度資料："+lng);
                    }
                    else
                    {
                        outputString="目前 GPS 資訊 : 手動更新GPS位置失敗！ currentLocation 為 null...";
                        textview_gps_information.setText(outputString);
                    }
                }
                catch (SecurityException ex)
                {
                    outputString="目前 GPS 資訊 : 手動更新GPS位置失敗！ 請檢查存取權限並開啟GPS功能....";
                    textview_gps_information.setText(outputString);
                }
            }
        });
        buttonShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double lat,lng;
                String lable="目前位置";
                String geoString,querryString,uriString;
                lat=currentLocation.getLatitude();
                lng=currentLocation.getLongitude();
                geoString="geo:"+lat+","+lng;
                querryString=lat+","+lng+"("+lable+")";
                uriString= Uri.encode(querryString);
                geoString=geoString+"?g="+uriString;
                Intent geoIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(geoString));
                startActivity(geoIntent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        try
        {
            lc.removeUpdates(hw6GPSActivity.this);
            Log.d("GPS:","GPS 自動更新功能已關閉");
        }
        catch (Exception ex)
        {
            Log.d("GPS:","注意！！GPS 自動更新功能關閉失敗");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int minReflashTime=1000;
        float minDistance =1.0f;
        try
        {
            bestProvider=lc.getBestProvider(new Criteria(),true);
            if (bestProvider!=null)
            {
                lc.requestLocationUpdates(bestProvider,minReflashTime,minDistance, hw6GPSActivity.this);
                Log.d("GPS:","GPS 自動更新功能已啟用");
            }
            else
            {
                Log.d("GPS:","注意！重新啟動GPS自動更新功能關閉操作失敗");
            }
        }
        catch (SecurityException ex)
        {
            Log.d("GPS:","重新啟動GPS自動更新功能操作失敗！請檢查存取權限並開啟GPS功能…");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_REQUSET_GPS)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                textview_gps_information.setText("目前 GPS 資訊 : "+"成功取得GPS裝置存取權限");
            }
            else
            {
                textview_gps_information.setText("目前 GPS 資訊 : "+"取得GPS裝置存取權限失敗！無法取得GPS位置資訊");
            }
        }

    }

    @Override
    public void onLocationChanged(Location locations) {
        double lat,lng;
        String outputString;
        currentLocation=locations;

            if (currentLocation !=null)
            {
                lat=currentLocation.getLatitude();
                lng=currentLocation.getLongitude();
                outputString="目前 GPS 資訊 : 手動更新成功，定位資訊提供者 ＃"+bestProvider;
                textview_gps_information.setText(outputString+"\n緯度資料: "+lat+"\n經度資料："+lng);
            }
            else
            {
                outputString="目前 GPS 資訊 : 手動更新GPS位置失敗！ currentLocation 為 null...";
                textview_gps_information.setText(outputString);
            }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}