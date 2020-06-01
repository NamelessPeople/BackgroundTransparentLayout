package com.github.namelesspeople.backgroundtransparentlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 类描述：
 *
 * @author ：无名之辈
 * @date ：2020/6/1
 */
public class BackgroundTransparentLinearLayout extends LinearLayout {
    private float radius = 10;
    private RectF roundRect;
    private Paint mPaint;
    private Paint mSecondPaint;
    private int quadrant;
    protected final int topRight = 1;
    protected final int topLeft = 2;
    protected final int bottomLeft = 4;
    protected final int bottomRight = 8;
    protected final int all = 15;

    public BackgroundTransparentLinearLayout(@NonNull Context context) {
        this(context, null);
    }

    public BackgroundTransparentLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackgroundTransparentLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BackgroundTransparentLayout);
        radius = typedArray.getDimension(R.styleable.BackgroundTransparentLayout_radius, dp2px(10));
        quadrant = typedArray.getInt(R.styleable.BackgroundTransparentLayout_quadrant, 15);
        typedArray.recycle();
        roundRect = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mSecondPaint = new Paint();
        mSecondPaint.setAntiAlias(true);
        mSecondPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        roundRect.set(0, 0, getWidth(), getHeight());
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayer(roundRect, mPaint);
        } else {
            canvas.saveLayer(roundRect, mPaint, Canvas.ALL_SAVE_FLAG);
        }

        if (quadrant == all) {
            canvas.drawRoundRect(roundRect, radius, radius, mPaint);
        } else {
            float[] radii = new float[8];
            if ((quadrant & bottomRight) == bottomRight) {
                //右下
                radii[4] = radius;
                radii[5] = radius;
            }
            if ((quadrant & bottomLeft) == bottomLeft) {
                //左下
                radii[6] = radius;
                radii[7] = radius;
            }
            if ((quadrant & topLeft) == topLeft) {
                //左上
                radii[0] = radius;
                radii[1] = radius;
            }
            if ((quadrant & topRight) == topRight) {
                //右上
                radii[2] = radius;
                radii[3] = radius;
            }
            Path path = new Path();
            path.addRoundRect(roundRect, radii, Path.Direction.CW);
            canvas.drawPath(path, mPaint);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayer(roundRect, mSecondPaint);
        } else {
            canvas.saveLayer(roundRect, mSecondPaint, Canvas.ALL_SAVE_FLAG);
        }
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
