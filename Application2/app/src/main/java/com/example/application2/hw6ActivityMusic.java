package com.example.application2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class hw6ActivityMusic extends AppCompatActivity implements  MediaPlayer.OnCompletionListener{
    private MediaPlayer player;
    private TextView textview_message;
    private Button button_stop,button_start,button_pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_music);

        textview_message=findViewById(R.id.textview_message);

        button_start=findViewById(R.id.button_start);
        button_pause=findViewById(R.id.button_pause);
        button_stop=findViewById(R.id.button_stop);

        player=MediaPlayer.create(hw6ActivityMusic.this,R.raw.isabella);
        //isabella is create by SLSMusic https://www.youtube.com/watch?v=NU1JWw5003U
        player.setOnCompletionListener(this);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player!=null){
                    if (player.isPlaying()!=true){
                        player.start();
                        textview_message.setText("播放狀態→播放中～");
                    }
                }

            }
        });
        button_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player!=null){
                    if (player.isPlaying()==true){
                        player.pause();
                        textview_message.setText("播放狀態→暫停");
                    }
                }

            }
        });
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player!=null){
                    player.stop();
                    textview_message.setText("播放狀態→停止播放");
                    try {
                        player.prepare();
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }

            }
        });

    }
    @Override
    public void onCompletion(MediaPlayer m){
        textview_message.setText("播放狀態→音樂播放完畢");
        player.seekTo(0);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player!=null){
            player.release();
            player=null;
        }
    }
}