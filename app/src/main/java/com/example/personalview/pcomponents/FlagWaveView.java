package com.example.personalview.pcomponents;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.personalview.R;

public class FlagWaveView extends View {
    private final int WIDTH = 200;// 横向网格数目
    private final int HEIGHT = 200;// 纵向网格数目
    private int COUNT = (WIDTH + 1) * (HEIGHT + 1);// 图像分块数目
    private float[] verts = new float[COUNT * 2];// 每两位用来保存一个交织点的坐标：第一个x,第二个y
    private float[] orig = new float[COUNT * 2];
    private Bitmap bitmap;
    private int A = 50;// 正弦函数振幅
    private float k = 1;// 正弦函数周期

    public FlagWaveView(Context context) {
        super(context);
        initView();
    }

    public FlagWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FlagWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlagWaveView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        int index = 0;

        for (int y = 0; y <= HEIGHT; y++) {
            float fy = height * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = width * x / WIDTH;
                orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy + 100;
                index = index + 1;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int j = 0; j <= HEIGHT; j++) {
            for (int i = 0; i <= WIDTH; i++) {
                verts[(j * (WIDTH + 1) + i) * 2 + 0] += 0;
                float offsetY = (float) Math.sin((float) i / WIDTH * 2 * Math.PI + Math.PI * k);
                verts[(j * (WIDTH + 1) + i) * 2 + 1] = orig[(j * WIDTH + i) * 2 + 1] + offsetY * A;
            }
        }
        canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
        k += 0.1F;
        invalidate();
    }

    public void setSwing(int A) {
        this.A = A;
        postInvalidate();
    }
}
