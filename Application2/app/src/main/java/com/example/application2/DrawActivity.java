package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Draw2D d=new Draw2D(DrawActivity.this);
        setContentView(d);

    }
}