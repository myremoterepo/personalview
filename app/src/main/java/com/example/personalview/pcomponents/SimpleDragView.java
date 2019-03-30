package com.example.personalview.pcomponents;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SimpleDragView extends View {
    private static final String TAG = SimpleDragView.class.getSimpleName();

    private int mLastX;
    private int mLastY;

    public SimpleDragView(Context context) {
        super(context);
    }

    public SimpleDragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleDragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleDragView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //(getX(), getY())坐标是触控点（子View）相对于SimpleDrawView（父View）的视图坐标
        //(getRawX(), getRawY())坐标是触控点相对于屏幕的Android坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offX = x - mLastX;
                int offY = y - mLastY;
                layout(getLeft() + offX, getTop() + offY, getRight() + offX, getBottom() + offY);
                break;
            default:
                break;
        }
        return true;
    }
}
