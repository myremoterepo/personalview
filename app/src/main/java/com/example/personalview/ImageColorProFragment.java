package com.example.personalview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ImageColorProFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageColorProFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Bitmap bitmap;
    private ImageView imageView;
    private float MID_VALUE = 127F;
    private float mHue = 0;
    private float mSaturation = 1;
    private float mLum = 1;

    public ImageColorProFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageColorProFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageColorProFragment newInstance(String param1, String param2) {
        ImageColorProFragment fragment = new ImageColorProFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_color, container, false);
        SeekBar hue = view.findViewById(R.id.seek_bar_hue);
        SeekBar saturation = view.findViewById(R.id.seek_bar_saturation);
        SeekBar lum = view.findViewById(R.id.seek_bar_lum);
        hue.setOnSeekBarChangeListener(this);
        saturation.setOnSeekBarChangeListener(this);
        lum.setOnSeekBarChangeListener(this);
        imageView = view.findViewById(R.id.img_color_pro);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        imageView.setImageBitmap(bitmap);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seek_bar_hue:
                mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seek_bar_saturation:
                mSaturation = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seek_bar_lum:
                mLum = progress * 1.0F / MID_VALUE;
                break;
            default:
                break;
        }
        imageView.setImageBitmap(handleImageEffect(mHue, mSaturation, mLum));
    }

    private Bitmap handleImageEffect(float mHue, float mSaturation, float mLum) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, mHue);
        hueMatrix.setRotate(1, mHue);
        hueMatrix.setRotate(2, mHue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(mSaturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(mLum, mLum, mLum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
