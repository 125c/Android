package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private SQLiteDbHelper sqlitelhelp;
    private Button button_insert,button_update,button_delete,button_show_all,button_exe;
    private TextView textview_student_id,textview_student_name,textview_student_score,textview_student_new_score,textview_student_new_sql,textview_dataoutput;
    private EditText edittext_student_id,edittext_student_name,edittext_student_score,edittext_student_new_score, edittext_sql;
    private String outputtitlel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        sqlitelhelp=new SQLiteDbHelper(SQLiteActivity.this,"Class");
        db=sqlitelhelp.getWritableDatabase();

//        textview_student_id=findViewById(R.id.textview_student_id);
//        textview_student_name=findViewById(R.id.textview_student_name);
//        textview_student_score=findViewById(R.id.textview_student_score);
//        textview_student_new_score=findViewById(R.id.textview_student_new_score);
//        textview_student_new_sql=findViewById(R.id.textview_student_new_sql);
//        textview_dataoutput=findViewById(R.id.textview_dataoutput);
        button_insert=findViewById(R.id.button_insert);
        button_update=findViewById(R.id.button_update);
        button_delete=findViewById(R.id.button_delete);
        button_show_all=findViewById(R.id.button_show_all);
        button_exe=findViewById(R.id.button_exe);
        edittext_student_id=findViewById(R.id.edittext_student_id);
        edittext_student_name=findViewById(R.id.edittext_student_name);
        edittext_student_score=findViewById(R.id.edittext_student_score);
        edittext_student_new_score=findViewById(R.id.edittext_student_new_score);
        edittext_sql =findViewById(R.id.edittext_sql);

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                String name;
                long result;
                double score;
                ContentValues cv=new ContentValues();
                id=Integer.parseInt(edittext_student_id.getText().toString());
                name=edittext_student_name.getText().toString();
                score=Double.parseDouble(edittext_student_score.getText().toString());
                cv.put("student_id",id);
                cv.put("student_name",name);
                cv.put("score",score);
                result=db.insert("students",null,cv);
                textview_dataoutput.setText(outputtitlel+"\n新增紀錄成功，id:"+result);
            }
        });
        button_show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] columnsName;
                String outPutString="";
                int i;
                Cursor c=db.rawQuery("select * from students",null);
                columnsName=c.getColumnNames();
                for (i=0;i<columnsName.length;i++){
                    outPutString +=columnsName[i]+"\t\t";
                }
                outPutString +="\n";
                c.moveToFirst();
                for (i=0;i<c.getCount();i++){
                    outPutString+=c.getString(0)+"\t\t";
                    outPutString+=c.getString(1)+"\t\t";
                    outPutString+=c.getString(2)+"\n";
                }
                textview_dataoutput.setText(outPutString);
            }
        });
    }

}