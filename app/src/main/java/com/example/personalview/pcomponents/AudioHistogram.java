package com.example.personalview.pcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AudioHistogram extends View {
    private static final String TAG = AudioHistogram.class.getSimpleName();
    private int mRectCount;
    private float mWidth;
    private float mRectWidth;
    private float mRectHeight;
    private float offset;
    private Paint mPaint;

    public AudioHistogram(Context context) {
        super(context);
        initParams();
    }

    public AudioHistogram(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public AudioHistogram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AudioHistogram(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizedChanged");
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth * 0.6 / mRectCount);
        offset = mRectWidth / 10;
        LinearGradient mLinearGradient = new LinearGradient(0, 0, mRectWidth, mRectHeight, Color.YELLOW, Color.BLUE, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw");
        for (int i = 0; i < mRectCount; i++) {
            double random = Math.random();
            float currentHeight = (float) (mRectHeight * random);
            canvas.drawRect(
                    (float) (mWidth * 0.4 / 2 + mRectWidth * i + offset),
                    currentHeight,
                    (float) (mWidth * 0.4 / 2 + mRectWidth * (i + 1)),
                    mRectHeight,
                    mPaint
            );
        }
        postInvalidateDelayed(300);
    }

    private void initParams() {
        mRectCount = 20;
        mPaint = new Paint();
    }


}
