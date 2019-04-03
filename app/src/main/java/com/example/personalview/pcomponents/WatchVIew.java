package com.example.personalview.pcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class WatchVIew extends View {
    private Paint paintCircle;
    private Paint paintDegree;
    private Paint paintHour;
    private Paint paintMini;
    private float mWidth;
    private float mHeight;

    public WatchVIew(Context context) {
        super(context);
        initParams();
    }

    public WatchVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public WatchVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WatchVIew(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paintCircle);
        for (int i = 0; i < 24; i++) {
            String degree = String.valueOf(i);
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                // long
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 60, paintDegree);
                canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2, mHeight / 2 - mWidth / 2 + 90, paintDegree);
            } else {
                // short
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 30, paintDegree);
                canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2, mHeight / 2 - mWidth / 2 + 60, paintDegree);
            }
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }
        canvas.save();

        canvas.translate(mWidth /2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMini);
        canvas.restore();
    }

    private void initParams() {
        // 表盘外圆
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);

        // 表盘刻度
        paintDegree = new Paint();

        // 表盘时针
        paintHour = new Paint();
        paintHour.setStrokeWidth(20);

        // 表盘分针
        paintMini = new Paint();
        paintMini.setStrokeWidth(10);
    }
}
