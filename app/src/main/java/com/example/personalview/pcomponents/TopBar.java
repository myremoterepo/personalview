package com.example.personalview.pcomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.personalview.R;

public class TopBar extends RelativeLayout {
    private static final String TAG = TopBar.class.getSimpleName();

    // attributes
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;
    private Drawable mTitleBackground;

    // widgets
    private Button mLeftBtn;
    private Button mRightBtn;
    private TextView mTitleView;

    // layout parameters
    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;

    // listener
    private TopBarClickListener mClickListener;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context) {
        super(context);
        Log.d(TAG, "constructor1");
        initView(context);// need attrs
        initClickListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "constructor2");
        parseAttrs(context, attrs);
        initView(context);
        initClickListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "constructor3");
        parseAttrs(context, attrs);
        initView(context);
        initClickListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TopBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "constructor4");
        parseAttrs(context, attrs);
        initView(context);
        initClickListener();
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_title);
        mTitleBackground = ta.getDrawable(R.styleable.TopBar_titleBackground);

        ta.recycle();//回收资源
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView(Context context) {
        mLeftBtn = new Button(context);
        mRightBtn = new Button(context);
        mTitleView = new TextView(context);

        mLeftBtn.setId(R.id.top_bar_left_button);
        mLeftBtn.setTextColor(mLeftTextColor);
        mLeftBtn.setBackground(mLeftBackground);
        mLeftBtn.setText(mLeftText);

        mRightBtn.setId(R.id.top_bar_right_button);
        mRightBtn.setTextColor(mRightTextColor);
        mRightBtn.setBackground(mRightBackground);
        mRightBtn.setText(mRightText);

        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setText(mTitle);
        mTitleView.setBackground(mTitleBackground);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftBtn, mLeftParams);

        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightBtn, mRightParams);

        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.RIGHT_OF, R.id.top_bar_left_button);
        mTitleParams.addRule(RelativeLayout.LEFT_OF, R.id.top_bar_right_button);
        addView(mTitleView, mTitleParams);
    }

    private void initClickListener() {
        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.leftClick();
                }
            }
        });

        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.rightClick();
                }
            }
        });
    }

    public void setClickListener(TopBarClickListener topBarClickListener) {
        mClickListener = topBarClickListener;
    }

    /**
     * make btn visible or gone
     * @param id target
     * @param flag true=visible, and false=gone
     */
    public void setBtnVisible(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftBtn.setVisibility(VISIBLE);
            } else {
                mRightBtn.setVisibility(VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftBtn.setVisibility(GONE);
            } else {
                mRightBtn.setVisibility(GONE);
            }
        }
    }

    public void setTitle(String s) {
        if (mTitleView != null) {
            mTitleView.setText(s);
        }
    }

    public interface TopBarClickListener {
        void leftClick();

        void rightClick();
    }
}
