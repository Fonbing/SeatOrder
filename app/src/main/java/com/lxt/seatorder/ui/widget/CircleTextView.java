package com.lxt.seatorder.ui.widget;

/**
 * Created by Lxt Jxfen on 2020/4/3.
 * email: 1771874056@qq.com
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;

import com.lxt.seatorder.R;

import java.util.Random;

import androidx.appcompat.widget.AppCompatTextView;

public class CircleTextView extends AppCompatTextView {

    private static final int DEFAULT_FILL_TYPE = 0;

    private float mRadius;
    private int ctBackgroundColor;
    private int borderColor;
    private float borderWidth;
    private float borderAlpha;
    private int ctType;
    private boolean ctColorRnd;

    private float mCornerRadius = 360;
    private float mDx = 0;
    private float mDy = 0;

    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public int getCtBackgroundColor() {
        return ctBackgroundColor;
    }

    public void setCtBackgroundColor(int backgroundColor) {
        this.ctBackgroundColor = backgroundColor;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.circleTextView);
        ctBackgroundColor = typedArray.getColor(R.styleable.circleTextView_ct_backgroundColor, Color.YELLOW);
        borderColor = typedArray.getColor(R.styleable.circleTextView_ct_border_color, Color.TRANSPARENT);
        borderWidth = typedArray.getDimension(R.styleable.circleTextView_ct_border_width, 0);
        borderAlpha = typedArray.getFloat(R.styleable.circleTextView_ct_border_alpha, 1);
        ctType = typedArray.getInt(R.styleable.circleTextView_ct_type, DEFAULT_FILL_TYPE);
        ctColorRnd = typedArray.getBoolean(R.styleable.circleTextView_ct_color_random, true);
        if (ctColorRnd) {
            ctBackgroundColor = genRndColor();
        }
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        mRadius = Math.min(getHeight(), getWidth()) / 2;

        if (ctType == 1) {
            setBackgroundCompat(getWidth(), getHeight());
        } else if (ctType == 2) {
            paint.setColor(borderColor);
            paint.setAntiAlias(true);
            paint.setAlpha((int) (255 * borderAlpha));
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, paint);
        } else {
            borderWidth = 0;
        }

        paint.setColor(ctBackgroundColor);
        paint.setAntiAlias(true);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius - borderWidth, paint);

        super.onDraw(canvas);
    }

    private int genRndColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return Color.rgb(r, g, b);
    }

    private void setBackgroundCompat(int w, int h) {
        Bitmap bitmap = createShadowBitmap(w, h, mCornerRadius, borderWidth + 5, mDx, mDy, borderColor);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
    }

    private Bitmap createShadowBitmap(int shadowWidth, int shadowHeight, float cornerRadius, float shadowRadius,
                                      float dx, float dy, int shadowColor) {

        Bitmap output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(output);

        RectF shadowRect = new RectF(
                shadowRadius,
                shadowRadius,
                shadowWidth - shadowRadius,
                shadowHeight - shadowRadius);

        if (dy > 0) {
            shadowRect.top += dy;
            shadowRect.bottom -= dy;
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy);
            shadowRect.bottom -= Math.abs(dy);
        }

        if (dx > 0) {
            shadowRect.left += dx;
            shadowRect.right -= dx;
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx);
            shadowRect.right -= Math.abs(dx);
        }

        Paint shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(shadowColor);
        shadowPaint.setStyle(Paint.Style.FILL);

        if (!isInEditMode()) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor);
        }

        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint);

        return output;
    }

}

