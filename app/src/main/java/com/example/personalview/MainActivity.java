package com.example.personalview;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.personalview.pcomponents.GradientTextView;
import com.example.personalview.pcomponents.RetTextView;
import com.example.personalview.pcomponents.CircleScaleView;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentInteractionListener {
    private static final String TAG_POP_FRAG = "pop";
    public static final String TAG_MAIN_FRAG = "main";

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
                scaleView.setSweepAngle(70);
                scaleFragment.setView(scaleView);
                FragmentTransaction transScale = getSupportFragmentManager().beginTransaction();
                transScale.replace(android.R.id.content, scaleFragment, TAG_POP_FRAG);
                transScale.addToBackStack(null);
                transScale.commit();
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
