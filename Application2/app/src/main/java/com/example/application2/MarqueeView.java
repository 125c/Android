package com.example.application2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MarqueeView extends View {
    Paint pen;
    Context context;
    String marqueeString;
    int screenWidth,screenHeight;
    float textWidth,textHeigh,xPosition,yPosition,runningSpeed;
    public MarqueeView(Context c){
        super(c);
        pen=new Paint();
        pen.setColor(Color.BLACK);
    }
    public MarqueeView(Context c,String textString,int width,int height){
        super(c);
        pen=new Paint();
        pen.setColor(Color.BLACK);
        pen.setTextAlign(Paint.Align.RIGHT);
        pen.setTextSize(50f);
        this.context=c;
        this.marqueeString=textString;
        this.screenWidth=width;
        this.screenHeight=height;
        this.textWidth=pen.measureText(textString);
        this.textHeigh=pen.getFontMetrics().descent-pen.getFontMetrics().ascent;//字的底部-字的高度
        this.xPosition=screenWidth+textWidth;
        this.yPosition=(screenHeight/2)-textHeigh;
        this.runningSpeed=yPosition/500;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int i;
        float y;
        super.onDraw(canvas);
        if(marqueeString.contains("\n")){//可以一次跑多行
            y=this.yPosition;
            String[] multiLinString=marqueeString.split("\n");
            for (i=0;i<multiLinString.length;i++){
                canvas.drawText(multiLinString[i],xPosition,y,pen);
                y+=this.textHeigh;
            }
        }else {
            canvas.drawText(marqueeString,xPosition,yPosition,pen);
        }

//        pen.setColor(Color.BLACK);
//        canvas.drawText(marqueeString,xPosition,yPosition,pen);
        xPosition-=runningSpeed;
        if (xPosition<(0-textWidth)){//這段可以重複跑
            xPosition=screenWidth+textWidth;//這段可以重複跑
        }
        invalidate();//這段可以重複跑
    }
}
