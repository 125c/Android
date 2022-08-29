package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HW6Activity extends AppCompatActivity {
    private Button button_MusicActivity,button_VedioActivity,button_CameraActivity,button_GPSActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw6);
        button_MusicActivity=findViewById(R.id.button_MusicActivity);
        button_VedioActivity=findViewById(R.id.button_VedioActivity);
        button_CameraActivity=findViewById(R.id.button_CameraActivity);
        button_GPSActivity=findViewById(R.id.button_GPSActivity);

        button_MusicActivity = (Button) findViewById(R.id.button_MusicActivity);
//        button_MusicActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(HW6Activity.this,HW6ActivityTest.class);
//            }
//        }
    }
}