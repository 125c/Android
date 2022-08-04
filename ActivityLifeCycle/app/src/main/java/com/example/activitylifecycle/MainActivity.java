package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("活動生命週期示範：","MainActivity - onCreate()方法已執行");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("活動生命週期示範：","MainActivity - onStart()方法已執行");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("活動生命週期示範：","MainActivity - onResume()方法已執行");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("活動生命週期示範：","MainActivity - onPause()方法已執行");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("活動生命週期示範：","MainActivity - onStop()方法已執行");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("活動生命週期示範：","MainActivity - onRestart()方法已執行");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("活動生命週期示範：","MainActivity - onDestroy()方法已執行");
    }
}