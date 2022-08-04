package com.example.intent03;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int SET_RESULT=100;
    EditText editText_opd1,editText_opd2;
    TextView textview_output;
    Button button_op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                editText_opd1=findViewById(R.id.edittext_opd1);
                editText_opd2=findViewById(R.id.edittext_opd2);
                textview_output=findViewById(R.id.textview_output);
                button_op=findViewById(R.id.button_op);
                button_op.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(MainActivity.this,Activity_op.class);
                        Bundle bundle=new Bundle();
                bundle.putString("OPERATND1",editText_opd1.getText().toString());
                bundle.putString("OPERATND2",editText_opd2.getText().toString());
                i.putExtras(bundle);
                startActivityForResult(i,SET_RESULT);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SET_RESULT) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                textview_output.setText("運算結果"+bundle.getDouble("CALCULATE_RESULT"));
            }
        }
    }
}
