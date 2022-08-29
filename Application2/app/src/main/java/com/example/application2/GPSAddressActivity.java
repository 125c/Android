package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class GPSAddressActivity extends AppCompatActivity {
    private EditText edittext_address;
    private TextView textview_result_gpd;
    private Button button_query_gps;
    private double lat, lng;
    private String addressName;
    private Geocoder g;
    private List<Address> gpsAddressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsaddress);
        textview_result_gpd=findViewById(R.id.textview_result_gpd);
        edittext_address=findViewById(R.id.edittext_address);
        button_query_gps=findViewById(R.id.button_query_gps);
        g=new Geocoder(GPSAddressActivity.this, Locale.TAIWAN);
        button_query_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressName=edittext_address.getText().toString();
                try {
                    gpsAddressList=g.getFromLocationName(addressName,1);
                    if(gpsAddressList!=null){
                        lat=gpsAddressList.get(0).getLatitude();
                        lng =gpsAddressList.get(0).getLongitude();
                        textview_result_gpd.setText("GPS座標查尋結果：\n 緯度："+lat+"\n經度："+ lng);
                    }else {
                        textview_result_gpd.setText("GPS座標查尋結果：\n gpsAddressList=null，無法取得gps座標值");
                    }
                }catch (Exception ex){
                    textview_result_gpd.setText("GPS座標查尋結果：\n例外狀況發生："+ex.toString());
                }

            }
        });

    }
}