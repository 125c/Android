package com.example.intent02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_f extends AppCompatActivity {
    Button button_close_activity_f;
    Double c,f;
    TextView textview_output_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f);
        button_close_activity_f=findViewById(R.id.button_close_activity_f);
        textview_output_f=findViewById(R.id.textview_output_f);


        Bundle bundle=this.getIntent().getExtras();
        if (bundle!=null) {
            c=Double.parseDouble(bundle.getString("Temp_C"));
            f=((9.0*c)/5.0)+32.0;
            textview_output_f.setText("華氏"+f);
        }

        button_close_activity_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}