package com.example.application2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Draw2D extends View {
    public Draw2D(Context c){
        super(c);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint pen=new Paint();
        pen.setStyle(Paint.Style.FILL);
        pen.setColor(Color.BLUE);
        canvas.drawPaint(pen);
        pen.setAntiAlias(true); //反鋸齒
        pen.setColor(Color.RED);
        canvas.drawCircle(40,40,30,pen);
        pen.setColor(Color.BLACK);
        canvas.drawRect(80,90,120,130,pen);//這是正方形
        pen.setColor(Color.GREEN);
        pen.setTextSize(30);
        canvas.drawText("世上只有冷氣好",150,200,pen);
        Resources res=this.getResources();
        Bitmap bitmap_picture= BitmapFactory.decodeResource(res,R.drawable.capoo);
        canvas.drawBitmap(bitmap_picture,200,400,pen);
        pen.setColor(Color.WHITE);
        pen.setTextSize(50);
        String str="雞皮～";
        canvas.rotate(-45,180,200);
        canvas.drawText(str,180,200,pen);
    }
}
