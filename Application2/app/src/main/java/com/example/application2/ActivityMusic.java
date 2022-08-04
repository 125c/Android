package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityMusic extends AppCompatActivity implements  MediaPlayer.OnCompletionListener{
    private MediaPlayer player;
    private TextView textview_message;
    private Button button_stop,button_start,button_pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activty_music);

        textview_message=findViewById(R.id.textview_message);

        button_start=findViewById(R.id.button_start);
        button_pause=findViewById(R.id.button_pause);
        button_stop=findViewById(R.id.button_stop);

        player=MediaPlayer.create(ActivityMusic.this,R.raw.isabella);
        player.setOnCompletionListener(this);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player!=null){
                    if (player.isPlaying()!=true){
                        player.start();
                        textview_message.setText("播放中～");
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
                        textview_message.setText("暫停");
                    }
                }

            }
        });
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player!=null){
                    player.stop();
                    textview_message.setText("停止播放");
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
        textview_message.setText("音樂播放完畢");
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