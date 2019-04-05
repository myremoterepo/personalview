package com.example.personalview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GridMatrixFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridMatrixFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Bitmap bitmap;
    private int mWidth;
    private int mHeight;
    private EditText[] mEts = new EditText[20];
    private GridLayout gridLayout;
    private float[] mColorMatrix = new float[20];
    private ImageView imageView;

    public GridMatrixFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GridMatrixFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GridMatrixFragment newInstance(String param1, String param2) {
        GridMatrixFragment fragment = new GridMatrixFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_matrix, container, false);
        gridLayout = view.findViewById(R.id.grid_color_matrix);
        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                mWidth = gridLayout.getWidth() / 5;
                mHeight = gridLayout.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
        imageView = view.findViewById(R.id.img_grid_color_matrix);
        imageView.setImageBitmap(bitmap);
        view.findViewById(R.id.btn_change).setOnClickListener(this);
        view.findViewById(R.id.btn_reset).setOnClickListener(this);
        return view;
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(getContext());
            mEts[i] = editText;
            gridLayout.addView(editText, mWidth, mHeight);
        }
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        for (int i = 0; i < 20; i ++) {
            Log.d("fan", mColorMatrix[i] + "");
        }
        colorMatrix.set(mColorMatrix);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        imageView.setImageBitmap(bmp);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change:
                getMatrix();
                setImageMatrix();
                break;

            case R.id.btn_reset:
                initMatrix();
                getMatrix();
                setImageMatrix();
                break;

            default:
                break;
        }
    }
}
