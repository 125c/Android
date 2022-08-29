package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    private VideoView video;
    private MediaController mController;
    private String videoFilename="point21";
    private String uriResourceTitle="android.resource://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoctivity);
        video=findViewById(R.id.videoview_main);
        int id=getResources().getIdentifier(videoFilename,"raw",this.getPackageName());
        String strVideoResource=uriResourceTitle+this.getPackageName()+"/"+id;
        video.setVideoURI(Uri.parse(strVideoResource));
        //或 Uri videoUri=Uri.parse(strVideoResource);
        //   video.setVideoURI(videoUri);
        mController =new MediaController(VideoActivity.this);
        video.setMediaController(mController);
        video.start();
    }
    @Override
    protected  void onPause(){//不管畫面移動到哪裡暫停狀態，移回後要按開始
        super.onPause();
        if (video!=null){
            if (video.isPlaying()==true){
                video.stopPlayback();
            }
        }
    }
}