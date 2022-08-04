package com.example.intent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void button_Click(View v)
    {
        Intent intenet = new Intent(this,Activity_Two.class);
        startActivity(intenet);
    }
    public void button_Click2(View v)
    {
        Intent intenet = new Intent(this,Activity_Three.class);
        startActivity(intenet);
    }
}