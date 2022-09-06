package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MarqueeActivity extends AppCompatActivity {
    DisplayMetrics display;
    int width,heigh,index;
    String marqueeString[]=new String[]{"java祭司 神殿 征戰 \n弓箭 是誰的從前","java喜歡在人潮中\n妳只屬於我的那畫面","java經過蘇美女神\n身邊 我以女神之名許願","java思念像底格里斯河\n般的蔓延","java當古文明只剩下難解的語言","java傳說就成了永垂不朽的詩篇"};
    String marque_string_array[];
    String stringDate;
    Timer t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_marquee);
        display=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        width=display.widthPixels;
        heigh=display.heightPixels;
        index=0;
        //marque_string_array=getResources().getStringArray(R.array.marque_string_array);
        stringDate=new SimpleDateFormat("yyyy-MM-d,h:m:s ").format(new Date());//MM不滿2位會補0。要24小時制改成HH:mm:ss
        this.setTitle(stringDate);
        MarqueeView v=new MarqueeView(MarqueeActivity.this,marqueeString[index],width,heigh);
        //MarqueeView v=new MarqueeView(MarqueeActivity.this,marque_string_array[index],width,heigh);
        setContentView(v);
        t=new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(index<(marqueeString.length-1)){//這裡也要換成marque_string_array 不然只會跑第一個item
                    index+=1;
                }else {
                    index=0;
                }
                MarqueeView v=new MarqueeView(MarqueeActivity.this,marqueeString[index],width,heigh);//這裡也要換成marque_string_array?
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(v);
                    }
                });
                //
//                if(index<(marque_string_array.length-1)){//這裡也要換成marque_string_array 不然只會跑第一個item
//                    index+=1;
//                }else {
//                    index=0;
//                }
//                MarqueeView v=new MarqueeView(MarqueeActivity.this,marque_string_array[index],width,heigh);//這裡也要換成marque_string_array?
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        setContentView(v);
//                    }
//                });
            }
        },35000,35000);
    }
}