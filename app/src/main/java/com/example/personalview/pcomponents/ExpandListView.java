package com.example.personalview.pcomponents;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

public class ExpandListView extends ListView {
    private int mMaxOverScrollY = 30;

    public ExpandListView(Context context) {
        super(context);
        initParams(context);
    }

    public ExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context);
    }

    public ExpandListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ExpandListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams(context);
    }

    private void initParams(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mMaxOverScrollY = (int) (metrics.density * mMaxOverScrollY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        // 当listview不能一屏将内容展示完时才会有回弹效果
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverScrollY, isTouchEvent);
    }
}
