package com.oldhigh.learnmeterialedittext.view09;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.oldhigh.learnmeterialedittext.R;
import com.oldhigh.learnmeterialedittext.Util;

/**
 * 圆形的view
 * @author oldhigh
 */
public class CircleView extends View {

    public static final float PADDING = Util.dpToPx(40);
    public static final float RADIUS = Util.dpToPx(100);

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = (int) (PADDING + RADIUS)*2;


//        setMeasuredDimension(size , size); 这是不对的

        setMeasuredDimension(resolveSizeAndState(size , widthMeasureSpec , 0) ,
                resolveSizeAndState(size , heightMeasureSpec , 0));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);
        canvas.drawCircle(PADDING + RADIUS ,PADDING + RADIUS , RADIUS,mPaint );
    }
}
