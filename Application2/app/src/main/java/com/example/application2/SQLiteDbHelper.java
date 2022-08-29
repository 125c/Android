package com.example.application2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="Class";
    private static final int DATABASE_VERSION=1;
    public  SQLiteDbHelper(Context c){
        super(c,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public  SQLiteDbHelper(Context c,String dbName){
        super(c,dbName,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String string_Sql="create table students (student_id INTEGER primary key,"+
                "student_name TEXT not null,"+
                "student_score REAL not null)";
        db.execSQL(string_Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
