package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HW6Activity extends AppCompatActivity {
//    private Button button_MusicActivity,button_VedioActivity,button_CameraActivity,button_GPSActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw6);
    }
        public void button_Click_Music(View v)
        {
            Intent intenet = new Intent(this,hw6ActivityMusic.class);
            startActivity(intenet);
        }
        public void button_Click_Video(View v)
        {
            Intent intenet = new Intent(this,hw6VideoActivity.class);
            startActivity(intenet);
        }
        public void button_Click_Camera(View v)
        {
            Intent intenet = new Intent(this,hw6CammeraActivity.class);
            startActivity(intenet);
        }
        public void button_Click_GPS(View v)
        {
            Intent intenet = new Intent(this,hw6GPSActivity.class);
            startActivity(intenet);
        }

//        button_MusicActivity=findViewById(R.id.button_MusicActivity);
//        button_VedioActivity=findViewById(R.id.button_VedioActivity);
//        button_CameraActivity=findViewById(R.id.button_CameraActivity);
//        button_GPSActivity=findViewById(R.id.button_GPSActivity);
//        button_MusicActivity = (Button) findViewById(R.id.button_MusicActivity);
//        button_MusicActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(HW6Activity.this,HW6ActivityTest.class);
//            }
//        }

}