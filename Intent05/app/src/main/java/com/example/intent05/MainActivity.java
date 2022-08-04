package com.example.intent05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  static final int GET_CONTACT=1;
    Button button_open_search,button_pick_contacts;
    EditText edittext_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_open_search=findViewById(R.id.button_open_search);
        button_pick_contacts=findViewById(R.id.button_pick_contacts);
        edittext_search=findViewById(R.id.edittext_search);
        button_open_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword=edittext_search.getText().toString();
                Intent i =new Intent(Intent.ACTION_WEB_SEARCH);
                i.putExtra(SearchManager.QUERY,keyword);
                startActivity(i);
            }
        });
        button_pick_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Intent.ACTION_PICK);
                i.setType(ContactsContract.Contacts.CONTENT_TYPE);
                startActivityForResult(i,GET_CONTACT);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==GET_CONTACT){
            if (resultCode==RESULT_OK){
                String contactStr=data.getData().toString();
                Toast.makeText(MainActivity.this,contactStr,Toast.LENGTH_SHORT).show();
                Intent i =new Intent(Intent.ACTION_VIEW, Uri.parse(contactStr));
                startActivity(i);
            }
        }
    }
}