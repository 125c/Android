package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSDecoActivity extends AppCompatActivity {
    private EditText edittextLatitude,edittextLongtitude;
    private TextView textview_result;
    private Button button_query;
    private double lat,lon;
    private Geocoder geo;
    private List<Address> list_address;
    private  final  int MAX_RESULT=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsdeco);
        edittextLatitude=findViewById(R.id.edittextLatitude);
        edittextLongtitude=findViewById(R.id.edittextLongtitude);
        textview_result=findViewById(R.id.textview_result);
        button_query=findViewById(R.id.button_query);
        geo=new Geocoder(GPSDecoActivity.this, Locale.TAIWAN);
        button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string_output;
                int i,j;
                lat=Double.parseDouble(edittextLatitude.getText().toString());
                lon=Double.parseDouble(edittextLongtitude.getText().toString());

                try {

                    list_address=geo.getFromLocation(lat,lon,MAX_RESULT);
                    if (list_address!=null){
                        string_output="";
                        for (i=0;i<MAX_RESULT;i++){
                            Address a=list_address.get(i);
                            string_output+=(i+1)+" : ";
                            for (j=0;j<=a.getMaxAddressLineIndex();j++){
                                string_output +=a.getAddressLine(j)+",";
                            }
                            string_output+="\n";
                        }
                        textview_result.setText("查詢結果:\n"+string_output);
                    }else {
                        textview_result.setText("查詢結果:\n"+"Address List=nill，該GPS座標位置沒有符合地址資料");
                    }
                }catch (Exception ex){
                    textview_result.setText("查詢結果:例外狀況及發生 "+ex.toString());
                }

            }
        });

    }
}