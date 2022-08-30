package com.example.application2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MarqueeView extends View {
    Paint pen;
    Context context;
    String marqueeString;
    int screenWidth,screenHeight;
    double textWidth,xPosition,yPosition,runningSpeed;
    public MarqueeView(Context c){
        super(c);
        pen=new Paint();
        pen.setColor(Color.BLACK);
    }
    public MarqueeView(Context c,String textString,int width,int height){
        super(c);
        pen=new Paint();
        pen.setColor(Color.BLACK);

        this.context=c;
        this.marqueeString=textString;
        this.screenWidth=width;
        this.textWidth=pen.measureText(textString);
        this.xPosition=screenWidth+textWidth;
        this.yPosition=(height/2);
        this.runningSpeed=yPosition/500;
    }
}
