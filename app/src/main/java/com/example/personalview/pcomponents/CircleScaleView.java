package com.example.personalview.pcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class CircleScaleView extends View {
    private float mCircleXY;
    private float mRadius;
    private RectF mArcRectF;
    private Paint mCirclePaint;
    private float mSweepAngle = 0F;
    private Paint mArcPaint;
    private Paint mTextPaint;
    private String mShowText;
    private float mShowTextSize;
    private float mSweepValue = 66;

    public CircleScaleView(Context context) {
        super(context);
        initParams(context);
    }

    public CircleScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context);
    }

    public CircleScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleScaleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        canvas.drawArc(mArcRectF, 270, mSweepAngle, false, mArcPaint);
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY, mCircleXY + (mShowTextSize / 4), mTextPaint);
    }

    private void initParams(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        float length = displayMetrics.widthPixels;

        mCircleXY = length / 2;
        mRadius = (float) (length * 0.5 / 2);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(
                android.R.color.holo_blue_bright));

        mArcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9));
        mSweepAngle = (mSweepValue / 100f) * 360f;
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(
                android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (length * 0.1));
        mArcPaint.setStyle(Paint.Style.STROKE);

        mShowText = "Scale View";
        mShowTextSize = 50;
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setSweepAngle(float sweepAngle) {
        if (sweepAngle != 0) {
            mSweepValue = sweepAngle;
        } else {
            mSweepValue = 25;
        }
        invalidate();
    }

    public void setContent(String content) {
        mShowText = content;
        invalidate();
    }

    public void setContextSize(float size) {
        mShowTextSize = size;
        invalidate();
    }
}
