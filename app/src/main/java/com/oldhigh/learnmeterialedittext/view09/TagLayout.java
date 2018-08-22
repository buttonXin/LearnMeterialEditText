package com.oldhigh.learnmeterialedittext.view09;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.oldhigh.learnmeterialedittext.L;

import java.util.Arrays;

public class TagLayout extends ViewGroup {

    private Rect[] childRect;

    public TagLayout(Context context) {
        super(context);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //所以的子view使用的最大宽度 ， 最大高度 ，每行的宽度 ， 最大高度
        int widthUsed = 0;
        int heightUsed = 0;
        int lineWidth = 0;
        int maxHeight = 0;

        int childCount = getChildCount();
        if (childRect == null) {
            childRect = new Rect[childCount];
        } else if (childRect.length < childCount) {
            childRect = Arrays.copyOf(childRect, childCount);
        }

        View child;
        Rect rect;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            //写这个方法需要 generateLayoutParams（attrs） 中进行创建对应的layoutparams
            measureChildWithMargins(child, widthMeasureSpec, lineWidth,
                    heightMeasureSpec, heightUsed);

            //判断当前的child 宽度 是否超过fuview了，超过了lineWidth= 0换行， 高度+= 最大的，
            if ( (child.getMeasuredWidth() + lineWidth + getPaddingStart() + getPaddingEnd())
                    > MeasureSpec.getSize(widthMeasureSpec)){
                lineWidth = 0;
                heightUsed += maxHeight;
                maxHeight = 0;

                measureChildWithMargins(child, widthMeasureSpec, lineWidth,
                        heightMeasureSpec, heightUsed);
            }

            rect = childRect[i];
            if (rect == null) {
                rect = childRect[i]= new Rect();
            }

            rect.set(lineWidth, heightUsed,
                    lineWidth + child.getMeasuredWidth(),
                    heightUsed + child.getMeasuredHeight());


            lineWidth += child.getMeasuredWidth();
            widthUsed = Math.max(widthUsed , lineWidth);
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());

        }

        int width = widthUsed;
        int height = maxHeight + heightUsed;


        setMeasuredDimension(resolveSizeAndState(width, widthMeasureSpec, 0),
                resolveSizeAndState(height, heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child;
        Rect rect;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            rect = childRect[i];
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
