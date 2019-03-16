package com.example.personalview;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopFragment extends Fragment {
    private static final String TAG = PopFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FrameLayout fl;
    private View mChildView;
    private String mParam2;
    private String mParam1;

    public PopFragment() {
        // Required empty public constructor
    }

    public static PopFragment newInstance(String param1, String param2) {
        PopFragment fragment = new PopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        // Inflate the common_title for this fragment
        View view = inflater.inflate(R.layout.fragment_pop, container, false);
        fl = view.findViewById(R.id.fl_parent);
        addChildView();
        ((TextView)(view.findViewById(R.id.tv_description))).setText(mParam2);
        ((TextView)(view.findViewById(R.id.tv_title))).setText(mParam1);
        return view;
    }

    private void addChildView() {
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        mChildView.setPadding(30, 10, 40, 10);
        if (mChildView != null) {
            fl.addView(mChildView, lp);
        }
    }

    public void setView(View childView) {
        Log.d(TAG, "setView");
        mChildView = childView;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
        if (mChildView != null && fl != null) {
            fl.removeView(mChildView);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");
        super.onDetach();
    }
}
