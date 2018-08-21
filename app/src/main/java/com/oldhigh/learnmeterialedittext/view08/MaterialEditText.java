package com.oldhigh.learnmeterialedittext.view08;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.oldhigh.learnmeterialedittext.L;
import com.oldhigh.learnmeterialedittext.R;
import com.oldhigh.learnmeterialedittext.Util;

/**
 * 自定义material edittext
 *
 * @author oldhigh
 */
public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {

    public static final int MATERIAL_PADDING = Util.dpToPx(10);
    public static final int MATERIAL_TEXT_SIZE = Util.dpToPx(10);
    public static final int MATERIAL_OFFSET_X_Y = Util.dpToPx(5);
    /**
     * 留出的图片的宽度
     */
    public static final int MATERIAL_IMAGE = Util.dpToPx(30);
    /**
     * 线的默认宽度 与 获取焦点的宽度
     */
    public static final int MATERIAL_LINE_DEFAULT = Util.dpToPx(1);
    public static final int MATERIAL_LINE = Util.dpToPx(2);


    private Paint paint;

    private Paint linePaint;
    /**
     * 默认不显示上面的 hint
     */
    private boolean hasTextChange = false;
    /**
     * 进度
     */
    private float percent;

    /**
     * 动画便宜效果
     */
    private int offsetAnimator = MATERIAL_PADDING;
    /**
     * 是否需要material
     */
    private boolean needMaterial = true;
    private boolean needImage = false;
    private int mLineColor;
    private Drawable mBackground;


    public MaterialEditText(Context context) {
        this(context, null);
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaterialEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        TypedArray colorArray = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.colorAccent});

        mLineColor = colorArray.getColor(0, Color.BLACK);

        needMaterial = typedArray.getBoolean(R.styleable.MaterialEditText_needMaterial, true);
        boolean needImage = typedArray.getBoolean(R.styleable.MaterialEditText_needImage, true);


        colorArray.recycle();
        typedArray.recycle();

        Util.e("time", needMaterial, mLineColor, Color.BLACK, needImage);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(MATERIAL_TEXT_SIZE);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(MATERIAL_LINE_DEFAULT);


        setBackground(null);

        if (needMaterial) {

            setPadding(getPaddingLeft(),
                    getPaddingTop() + MATERIAL_PADDING,
                    getPaddingRight(), getPaddingBottom());

            setNeedImage(needImage);

        }


        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!needMaterial) {
                    return;
                }

                if (editable.length() > 0 && !hasTextChange) {
                    hasTextChange = true;
                    ObjectAnimator
                            .ofFloat(MaterialEditText.this, "percent", 0, 1)
                            .start();
                } else if (editable.length() <= 0) {
                    hasTextChange = false;
                    ObjectAnimator
                            .ofFloat(MaterialEditText.this, "percent", 1, 0)
                            .start();
                }
            }
        });

        mBackground = getBackground();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String hint = (String) getHint();
        int alpha = paint.getAlpha();
        L.e("alpha", alpha, needImage, hasFocus());
        //动画效果是从透明变不透明，或者反着
        paint.setAlpha((int) (percent * 255));

        //这里的是 1 - 百分比  动画效果是从下往上出现
        int offsetY = (int) (MATERIAL_PADDING * (1 - percent));

        int imageX = 0;
        //是否需要留点image的空间
        if (needImage) {
            imageX = MATERIAL_IMAGE;
        } else {
            imageX = 0;
        }

        //画文字
        canvas.drawText(hint, 0, hint.length(), MATERIAL_OFFSET_X_Y + imageX,
                MATERIAL_PADDING + MATERIAL_TEXT_SIZE - MATERIAL_OFFSET_X_Y + offsetY, paint);
        paint.setAlpha(alpha);

        //画下面的横线
        if (hasFocus()) {
            linePaint.setStrokeWidth(MATERIAL_LINE);
            linePaint.setColor(mLineColor);
        } else {
            linePaint.setStrokeWidth(MATERIAL_LINE_DEFAULT);
            linePaint.setColor(Color.BLACK);
        }
        canvas.drawLine(MATERIAL_OFFSET_X_Y + imageX,
                getBottom() - MATERIAL_OFFSET_X_Y,
                getRight(),
                getBottom() - MATERIAL_OFFSET_X_Y, linePaint);

    }


    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }

    public int getOffsetAnimator() {
        return offsetAnimator;
    }

    public void setOffsetAnimator(int offsetAnimator) {
        this.offsetAnimator = offsetAnimator;
        invalidate();
    }

    public boolean isNeedImage() {
        return needImage;
    }

    public void setNeedImage(boolean needImage) {
        if (this.needImage == needImage) {
            return;
        }

        this.needImage = needImage;
        requestLayout();

        if (this.needImage) {
            setPadding(getPaddingLeft() + MATERIAL_IMAGE, getPaddingTop(),
                    getPaddingRight(), getPaddingBottom());
        } else {
            setPadding(getPaddingLeft() - MATERIAL_IMAGE, getPaddingTop(),
                    getPaddingRight(), getPaddingBottom());
        }

    }
}
















