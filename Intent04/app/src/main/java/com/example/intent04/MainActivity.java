package com.example.intent04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.lang.UProperty;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button_open_browser,button_open_phone,button_open_map;
    EditText edittext_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_open_browser=findViewById(R.id.button_open_browser);
        button_open_phone=findViewById(R.id.button_open_phone);
        button_open_map=findViewById(R.id.button_open_map);
        edittext_url=findViewById(R.id.edittext_url);
        button_open_browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=edittext_url.getText().toString();
                Intent i =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });
        button_open_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:+886914100118"));
                startActivity(i);
            }
        });
        button_open_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("geo:22.6395, 120.3024"));
                startActivity(i);
            }
        });
    }
}