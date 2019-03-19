package com.example.personalview.pcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

public class SimpleScrollView extends ViewGroup {
    private static final String TAG = SimpleScrollView.class.getSimpleName();

    private int mScreenHeight;
    private int mLastY;
    private int mStart;
    private Scroller mScroller;
    private int mEnd;
    private int mHeight;//这里存了计算后的内容高度，理论上可以通过getHeight获取到，但是不知道什么原因获取的值不对。

    public SimpleScrollView(Context context) {
        super(context);
        initParams();
    }

    public SimpleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public SimpleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        Log.d(TAG, "count =" + getChildCount());
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
        mHeight = mScreenHeight * getChildCount();
        marginLayoutParams.height = mHeight;
        setLayoutParams(marginLayoutParams);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
        Log.d(TAG, "onLayout height" + getHeight() + ", measureheight=" + getMeasuredHeight() + ", mScreenHeight=" + mScreenHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();
                Log.d(TAG, "action down mLastY=" + mLastY + ", mStart=" + mStart);
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                int scrollY = getScrollY();
                if (scrollY < 0) {
                    dy = 0;
                }
                if (scrollY > mHeight - mScreenHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if (dScrollY > 0) {
                    if (dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);
                    }
                } else {
                    if (-dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void initParams() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;

        mScroller = new Scroller(getContext());
    }

}
