package com.example.personalview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.personalview.pcomponents.TopBar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.MainFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements TopBar.TopBarClickListener, MainAdapter.MainAdapterClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int TYPE_TV_RET = 0;
    public static final int TYPE_TV_GRADIENT = 1;
    public static final int TYPE_VIEW_SCALE = 2;
    public static final int TYPE_HISTOGRAM = 3;
    public static final int TYPE_SCROLL_VIEW = 4;
    public static final int TYPE_DRAG_VIEW = 5;
    public static final int TYPE_SLIDING_PANEL = 6;
    public static final int TYPE_WATCH_VIEW = 7;
    public static final int TYPE_IMAGE_COLOR_PROPERTY = 8;
    public static final int TYPE_GRID_COLOR_MATRIX = 9;
    public static final int TYPE_FLAG_WAVE = 10;
    public static final int TYPE_XFERMODE_VIEW = 11;
    public static final int TYPE_DRAWING_TABLE = 12;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("fan", "create MainFragment");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the common_title for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = view.findViewById(R.id.list_content);
        MainAdapter mainAdapter = new MainAdapter(getContext());
        List<String> btns = new ArrayList<>();
        btns.add("Ret Text View");
        btns.add("Gradient Text View");
        btns.add("Scale View");
        btns.add("Audio Histogram");
        btns.add("Simple Scroll View");
        btns.add("Simple Drag View");
        btns.add("Simple Sliding Panel Layout");
        btns.add("Watch View");
        btns.add("Image Color Property");
        btns.add("Grid Color Matrix");
        btns.add("FLAG WAVE");
        btns.add("Xfermode View");
        btns.add("Drawing Table");

        mainAdapter.setBtns(btns);
        mainAdapter.setListItemListener(this);
        listView.setAdapter(mainAdapter);
        TopBar topBar = view.findViewById(R.id.top_bar);
        topBar.setBtnVisible(1, false);
        topBar.setTitle(mParam1 + " " + mParam2);
        topBar.setClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentInteractionListener) {
            mListener = (MainFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void leftClick() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    @Override
    public void rightClick() {
        Log.d("fan", "rightClick");
    }

    @Override
    public void onBtnClick(int position) {
        Toast.makeText(getContext(), "click " + position, Toast.LENGTH_SHORT).show();
        if (mListener == null) {
            return;
        }
        switch (position) {
            case 0:
                mListener.onCreateView(TYPE_TV_RET);
                break;
            case 1:
                mListener.onCreateView(TYPE_TV_GRADIENT);
                break;
            case 2:
                mListener.onCreateView(TYPE_VIEW_SCALE);
                break;
            case 3:
                mListener.onCreateView(TYPE_HISTOGRAM);
                break;
            case 4:
                mListener.onCreateView(TYPE_SCROLL_VIEW);
                break;
            case 5:
                mListener.onCreateView(TYPE_DRAG_VIEW);
                break;
            case 6:
                mListener.onCreateView(TYPE_SLIDING_PANEL);
                break;
            case 7:
                mListener.onCreateView(TYPE_WATCH_VIEW);
                break;
            case 8:
                mListener.onCreateView(TYPE_IMAGE_COLOR_PROPERTY);
                break;
            case 9:
                mListener.onCreateView(TYPE_GRID_COLOR_MATRIX);
                break;
            case 10:
                mListener.onCreateView(TYPE_FLAG_WAVE);
                break;
            case 11:
                mListener.onCreateView(TYPE_XFERMODE_VIEW);
                break;
            case 12:
                mListener.onCreateView(TYPE_DRAWING_TABLE);
                break;
            default:
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface MainFragmentInteractionListener {
        void onCreateView(int type);
    }
}
