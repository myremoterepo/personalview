package com.example.personalview.pcomponents;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class SimpleSlidingPanelLayout extends FrameLayout {
    private ViewDragHelper mDragHelper;
    private View mMainView;
    private View mMenuView;
    private int mWidth;
    private int mLastX;

    public SimpleSlidingPanelLayout(Context context) {
        super(context);
        initView();
    }

    public SimpleSlidingPanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SimpleSlidingPanelLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleSlidingPanelLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        mDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View view, int i) {
                return mMainView == view;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                return left;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (mMainView.getLeft() < 500) {
                    // close
                    mDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                    ViewCompat.postInvalidateOnAnimation(SimpleSlidingPanelLayout.this);
                } else {
                    //open
                    mDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                    ViewCompat.postInvalidateOnAnimation(SimpleSlidingPanelLayout.this);

                }
            }
        });
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mDragHelper.processTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                int offX = x - mLastX;
                if (offX < 0) {
                    //禁止向左滑动
                } else {
                    mDragHelper.processTouchEvent(event);
                }
                break;
            default:
                mDragHelper.processTouchEvent(event);
                break;
        }

        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
