package com.oldhigh.learnmeterialedittext.view09;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int min = Math.min(measuredHeight, measuredWidth);
        setMeasuredDimension(min, min);

        //这个是让用户省去 AS_MOST 等的判断
//        setMeasuredDimension(resolveSize(min, widthMeasureSpec), resolveSize(min, heightMeasureSpec));

        //这多个参数的意思是 判断实际的view 是否被压缩了？来让父view 进行处理
//        setMeasuredDimension(resolveSizeAndState(min, widthMeasureSpec , 0),
//                resolveSizeAndState(min, heightMeasureSpec, 0));
    }
}
