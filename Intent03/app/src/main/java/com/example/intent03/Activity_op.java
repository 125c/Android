package com.example.intent03;

import static java.lang.Math.floor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Activity_op extends AppCompatActivity {
    Button button_calculate;
    double opd1,opd2,result;
    RadioButton rdbAdd,rdbSubtract,rdbMultiply,rdbDivide;
    CheckBox chkDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_op_java);
        rdbAdd=findViewById(R.id.radiobutton_add);
        rdbSubtract=findViewById(R.id.radiobutton_subtract);
        rdbMultiply=findViewById(R.id.radiobutton_multiply);
        rdbDivide=findViewById(R.id.radiobutton_divide);
        chkDivide=findViewById(R.id.checkbox_divide);
        button_calculate=findViewById(R.id.button_calculate);
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=Activity_op.this.getIntent().getExtras();
                if (bundle==null){
                    return;
                }
                opd1=Double.parseDouble(bundle.getString("OPERATND1"));
                opd2=Double.parseDouble(bundle.getString("OPERATND2"));

                if (rdbAdd.isChecked()){
                    result=opd1+opd2;
                }
                if (rdbSubtract.isChecked()){
                    result=opd1-opd2;
                }
                if (rdbMultiply.isChecked()){
                    result=opd1*opd2;
                }
                if (rdbDivide.isChecked()){
                    if (chkDivide.isChecked()){
                        result=(floor(opd1))/(floor(opd2));
                    }else{
                        result=opd1/opd2;
                    }
                }
                Intent return_Intent=new Intent();
                Bundle return_Bundle=new Bundle();
                return_Bundle.putDouble("CALCULATE_RESULT",result);
                return_Intent.putExtras(return_Bundle);
                setResult(RESULT_OK,return_Intent);
                finish();

            }

        });
    }
}