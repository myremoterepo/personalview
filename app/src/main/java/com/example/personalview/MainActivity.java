package com.example.personalview;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.personalview.pcomponents.AudioHistogram;
import com.example.personalview.pcomponents.CircleScaleView;
import com.example.personalview.pcomponents.GradientTextView;
import com.example.personalview.pcomponents.RetTextView;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentInteractionListener {
    private static final String TAG_POP_FRAG = "pop";
    public static final String TAG_MAIN_FRAG = "main";
    public static final String TAG_SEEK_COLOR_MATRIX_FRAG = "seek-color-matrix";
    public static final String TAG_GRID_COLOR_MATRIX_FRAG = "grid-color-matrix";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.common_title.activity_main);//use android.R.id.content, a framelayout

        if (savedInstanceState == null) {
            MainFragment mainFragment = MainFragment.newInstance("PersonalViews", "ViewList");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content, mainFragment, TAG_MAIN_FRAG);
            transaction.commit();
        }
    }

    @Override
    public void onCreateView(int type) {
        switch (type) {
            case MainFragment.TYPE_TV_RET:
                PopFragment popFragment = PopFragment.newInstance("PersonalViews", "RetTextView");
                RetTextView retTextView = new RetTextView(this);
                retTextView.setText(R.string.android);
                popFragment.setView(retTextView);
                FragmentTransaction transRet = getSupportFragmentManager().beginTransaction();
                transRet.replace(android.R.id.content, popFragment, TAG_POP_FRAG);
                transRet.addToBackStack(null);
                transRet.commit();
                break;
            case MainFragment.TYPE_TV_GRADIENT:
                PopFragment gradientFragment = PopFragment.newInstance("PersonnalViews", "LinearGradientTextView");
                GradientTextView gradientTextView = new GradientTextView(this);
                gradientTextView.setText(getString(R.string.gradient_tv));
                gradientFragment.setView(gradientTextView);
                FragmentTransaction transGradient = getSupportFragmentManager().beginTransaction();
                transGradient.replace(android.R.id.content, gradientFragment, TAG_POP_FRAG);
                transGradient.addToBackStack(null);
                transGradient.commit();
                break;
            case MainFragment.TYPE_VIEW_SCALE:
                PopFragment scaleFragment = PopFragment.newInstance("PersonnalViews", "ScaleView");
                CircleScaleView scaleView = new CircleScaleView(this);
                scaleView.setSweepAngle(90);
                scaleFragment.setView(scaleView);
                FragmentTransaction transScale = getSupportFragmentManager().beginTransaction();
                transScale.replace(android.R.id.content, scaleFragment, TAG_POP_FRAG);
                transScale.addToBackStack(null);
                transScale.commit();
                break;
            case MainFragment.TYPE_HISTOGRAM:
                PopFragment histogramFragment = PopFragment.newInstance("PersonnalViews", "AudioHistogram");
                AudioHistogram audioHistogram = new AudioHistogram(this);
                histogramFragment.setView(audioHistogram);
                FragmentTransaction transHistogram = getSupportFragmentManager().beginTransaction();
                transHistogram.replace(android.R.id.content, histogramFragment, TAG_POP_FRAG);
                transHistogram.addToBackStack(null);
                transHistogram.commit();
                break;
            case MainFragment.TYPE_SCROLL_VIEW:
                PopFragment scrollFrag = PopFragment.newInstance("PersonnalViews", "ScrollView");
                FragmentTransaction transScroll = getSupportFragmentManager().beginTransaction();
                transScroll.replace(android.R.id.content, scrollFrag, TAG_POP_FRAG);
                transScroll.addToBackStack(null);
                transScroll.commit();
                break;
            case MainFragment.TYPE_DRAG_VIEW:
                PopFragment dragFrag = PopFragment.newInstance("PersonnalViews", "DragView");
                FragmentTransaction transDrag = getSupportFragmentManager().beginTransaction();
                transDrag.replace(android.R.id.content, dragFrag, TAG_POP_FRAG);
                transDrag.addToBackStack(null);
                transDrag.commit();
                break;
            case MainFragment.TYPE_SLIDING_PANEL:
                PopFragment slidingFrag = PopFragment.newInstance("PersonnalViews", "SlidingPanel");
                FragmentTransaction transSliding = getSupportFragmentManager().beginTransaction();
                transSliding.replace(android.R.id.content, slidingFrag, TAG_POP_FRAG);
                transSliding.addToBackStack(null);
                transSliding.commit();
                break;
            case MainFragment.TYPE_WATCH_VIEW:
                PopFragment watchFrag = PopFragment.newInstance("PersonnalViews", "WatchView");
                FragmentTransaction transWatch = getSupportFragmentManager().beginTransaction();
                transWatch.replace(android.R.id.content, watchFrag, TAG_POP_FRAG);
                transWatch.addToBackStack(null);
                transWatch.commit();
            case MainFragment.TYPE_IMAGE_COLOR_PROPERTY:
                ImageColorProFragment imageFrag = ImageColorProFragment.newInstance("PersonnalViews", "ImageColorPro");
                FragmentTransaction transImage = getSupportFragmentManager().beginTransaction();
                transImage.replace(android.R.id.content, imageFrag, TAG_SEEK_COLOR_MATRIX_FRAG);
                transImage.addToBackStack(null);
                transImage.commit();
                break;
            case MainFragment.TYPE_GRID_COLOR_MATRIX:
                GridMatrixFragment gridMatrixFragment = GridMatrixFragment.newInstance("PersonalView", "GridColorMatrix");
                FragmentTransaction transGrid = getSupportFragmentManager().beginTransaction();
                transGrid.replace(android.R.id.content, gridMatrixFragment, TAG_GRID_COLOR_MATRIX_FRAG);
                transGrid.addToBackStack(null);
                transGrid.commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
