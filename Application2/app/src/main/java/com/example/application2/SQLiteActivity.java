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
    private static final  String DATATABLE_NAME ="students";
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
        textview_dataoutput=findViewById(R.id.textview_dataoutput);
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
                cv.put("student_score",score);
                result=db.insert("students",null,cv);
                if(result!=-1){
                    textview_dataoutput.setText(outputtitlel+"\n新增紀錄成功，id:"+result);
                }else {
                    textview_dataoutput.setText(outputtitlel+"\n新增紀錄失敗");
                }

            }
        });
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id,result,count;
                String newName;
                double newGrade;
                ContentValues cv;
                String[] newDataColumns;

                id = Integer.parseInt(edittext_student_id.getText().toString());
                newGrade = Double.parseDouble(edittext_student_new_score.getText().toString());
                newName=edittext_student_name.getText().toString();
                cv = new ContentValues();
                cv.put("student_score",newGrade);
                cv.put("student_name",newName);
                count = db.update(DATATABLE_NAME, cv ,"student_id = " + id,null);
                if (count > 0) {
                    textview_dataoutput.setText(outputtitlel + "\n更新紀錄成功，更新資料筆數 : " + count);
                    newDataColumns = getDataRow("SELECT * FROM "+ DATATABLE_NAME + " WHERE student_id = " + id);
                    edittext_student_id.setText(String.format("%04d",Integer.parseInt(newDataColumns[0])));
                    edittext_student_name.setText(newDataColumns[1]);
                    edittext_student_score.setText(newDataColumns[2]);
                } else {
                    textview_dataoutput.setText(outputtitlel + "\n更新紀錄失敗");
                }
            }
        });
        button_show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] columnsName;
                String outPutString="";
                String sqlCommendString="select * from students";
                int i;
                Cursor c=db.rawQuery(sqlCommendString,null);
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
                    c.moveToNext();
                }
                textview_dataoutput.setText(outPutString);

            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id,count;
                id=Integer.parseInt(edittext_student_id.getText().toString());
                count=db.delete(DATATABLE_NAME,"student_id="+id,null);
                if (count>0){
                    textview_dataoutput.setText(outputtitlel+"\n刪除成功，資料刪除筆數："+count);
                }else {
                    textview_dataoutput.setText(outputtitlel+"\n刪除失敗");
                }
            }
        });
        button_exe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sqlCommand;
                sqlCommand=edittext_sql.getText().toString();
                db.execSQL(sqlCommand);
            }
        });

    }
    public String[] getDataRow(String sql) {
        String[] dataColumns;
        String[] columnNames;
        int i,columnsCount;
        Cursor c;

        c = db.rawQuery(sql,null);
        c.moveToFirst();
        columnsCount = c.getColumnCount();
        dataColumns = new String[columnsCount];

        if (c.getCount() == 1) {
            for(i = 0 ; i < columnsCount ; i++){
                dataColumns[i] = c.getString(i);
            }
        }

        return dataColumns;
    }
    @Override
    protected void onStop(){
        super.onStop();
        db.close();
        db=null;
        //1.要有把資料庫關閉才能寫入2.DeviceFileExplorer要右鍵同步3.儲存的時候要給附檔名.db4.跟onCreate平行
    }

}