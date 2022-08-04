package com.example.intent02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button_ctof;
    EditText editText_input_c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_ctof =findViewById(R.id.button_ctof);
        editText_input_c=findViewById(R.id.edittext_input_c);

        button_ctof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Activity_f.class);
                Bundle bundle=new Bundle();
                bundle.putString("Temp_C",editText_input_c.getText().toString());
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}