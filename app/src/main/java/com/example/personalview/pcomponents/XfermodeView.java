package com.example.personalview.pcomponents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.personalview.R;

public class XfermodeView extends View {
    private Bitmap mBgBmp;
    private Bitmap mFgBmp;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;

    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();
        mBgBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        mFgBmp = Bitmap.createBitmap(mBgBmp.getWidth(), mBgBmp.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mFgBmp);
        mCanvas.drawColor(Color.GRAY);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBmp, 0, 0, null);
        canvas.drawBitmap(mFgBmp, 0, 0, null);
    }
}
