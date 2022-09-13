package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class MultiMusicActivity extends AppCompatActivity {
TextView textview_Title,textview_message_output;
Spinner spinner_music_list;
MediaPlayer mediaPlayer_01;
String stringSelectecMusic,stringMessageTitle="播放狀態：";
String[] arrayMusicList;
int i,intSelectedMusic;
int[] arrayMusicId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_music);
        textview_message_output=findViewById(R.id.textview_message_output);
        spinner_music_list=findViewById(R.id.spinner_music_list);
        arrayMusicList=getResources().getStringArray(R.array.multi_music_list);
        arrayMusicId=new int[arrayMusicList.length];
        for (i=0;i<arrayMusicList.length;i++){
            arrayMusicId[i]=getResources().getIdentifier(arrayMusicList[i],"raw",getPackageName());
        }
        intSelectedMusic=arrayMusicId[0];
        spinner_music_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intSelectedMusic=arrayMusicId[position];
                stringSelectecMusic=arrayMusicList[position];
                textview_message_output.setText(stringMessageTitle+"正在播放 - "+stringSelectecMusic);
                if (mediaPlayer_01==null){
                    mediaPlayer_01=MediaPlayer.create(MultiMusicActivity.this,arrayMusicId[position]);
                    mediaPlayer_01.start();
                }else {
                    mediaPlayer_01.pause();
                    mediaPlayer_01.stop();
                    mediaPlayer_01.release();
                    mediaPlayer_01=null;
                    mediaPlayer_01=MediaPlayer.create(MultiMusicActivity.this,arrayMusicId[position]);
                    mediaPlayer_01.start();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected  void  onPause(){
        super.onPause();
        try {
            if((mediaPlayer_01 !=null) &&(mediaPlayer_01.isPlaying())){
                mediaPlayer_01.pause();
            }
        }catch (IllegalStateException ex){
            ex.printStackTrace();
        }
    }
    @Override
    protected  void  onResume(){
        super.onResume();
        try {
            if (mediaPlayer_01==null){
                mediaPlayer_01=MediaPlayer.create(MultiMusicActivity.this,intSelectedMusic);
                mediaPlayer_01.start();
            }else {
                mediaPlayer_01.start();
            }
        }catch (IllegalStateException ex){
            ex.printStackTrace();
        }
    }
    @Override
    protected  void  onDestroy(){
        super.onDestroy();
        try {
            if (mediaPlayer_01!=null){
               if (mediaPlayer_01.isPlaying()){
                   mediaPlayer_01.pause();
                   mediaPlayer_01.stop();
               }
               mediaPlayer_01.release();
               mediaPlayer_01=null;
            }
        }catch (IllegalStateException ex){
            ex.printStackTrace();
        }
    }
}