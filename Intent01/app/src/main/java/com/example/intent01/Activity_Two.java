package com.example.intent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity_Two extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }
    public void button_Close_Click(View v)
    {
        finish();
    }
    public void  button_Click2(View v){
        Intent intent=new Intent(this,Activity_Two.class);
        startActivity(intent);
    }
}