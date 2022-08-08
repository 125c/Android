package com.example.application2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CammeraActivity extends AppCompatActivity {
    private ImageView imageview_photo;
    private Button button_start_cammera;
    private static final int REQUEST_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cammera);
        imageview_photo=findViewById(R.id.imageview_photo);
        button_start_cammera=findViewById(R.id.button_start_cammera);
        button_start_cammera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,REQUEST_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_IMAGE){
            if (resultCode==RESULT_OK){
                Bitmap bitmapImage=(Bitmap) data.getExtras().get("data");
                imageview_photo.setImageBitmap(bitmapImage);
            }
        }
    }
}