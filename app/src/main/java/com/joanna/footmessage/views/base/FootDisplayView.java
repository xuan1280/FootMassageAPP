package com.joanna.footmessage.views.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.Log;
import android.view.View;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.entities.PressurePosition;


@SuppressLint("ViewConstructor")
public class FootDisplayView extends View {
    private static final String TAG = "FootDisplayView";
    private Context context;
    private Paint paint;
    private Bitmap bitmap;
    private int viewWidth, viewHeight;
    private PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
    private PressurePosition[] pressurePositions = new PressurePosition[8];
    private boolean drawing;


    public FootDisplayView(Context context, int viewWidth, int viewHeight) {
        super(context);
        this.context = context;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        drawing = true;
        setBackgroundColor(Color.WHITE);
        init();
        createThreadForLoopingDrawing();
    }

    private void createThreadForLoopingDrawing() {
        new Thread(() -> {
            try {
                while (drawing) {
                    Thread.sleep(500);
                    postInvalidate();
                }
            } catch (InterruptedException err) {
                createThreadForLoopingDrawing();
            }
        }).start();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.right_foot);
        paint = new Paint();
        paint.setAntiAlias(true);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
        set8PressurePositions();
    }

    private void set8PressurePositions() {
        float centerX = viewWidth / 2f;
        float centerY = viewHeight / 2f;
        pressurePositions[0] = new PressurePosition(0, centerX - 120, centerY - 30);
        pressurePositions[1] = new PressurePosition(1, centerX - 150, centerY + 40);
        pressurePositions[2] = new PressurePosition(2, centerX - 90, centerY + 40);
        pressurePositions[3] = new PressurePosition(3, centerX + 80, centerY + 70);
        pressurePositions[4] = new PressurePosition(4, centerX + 70, centerY - 70);
        pressurePositions[5] = new PressurePosition(5, centerX + 150, centerY - 70);
        pressurePositions[6] = new PressurePosition(6, centerX + 70, centerY);
        pressurePositions[7] = new PressurePosition(7, centerX + 150, centerY);
    }

    public Bitmap zoomBitmap(Bitmap bitmap, float r) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(r, r);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        bitmap = zoomBitmap(bitmap, (float) canvasHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, (canvasWidth - bitmap.getWidth()) / 2, 0, paint);
        paint.setXfermode(porterDuffXfermode);

        for (PressurePosition pressurePosition : pressurePositions) {
            // red(255,0,0) yellow(255,255,0) green(0,255,0)
            int v = pressurePosition.getValue() * 10;
            int color = Color.TRANSPARENT;
            if (v > 255) color = Color.rgb(255, 255 - v, 0);
            else if (v < 255 && v > 0) color = Color.rgb(v, 255, 0);
            paint.setShader(new RadialGradient(pressurePosition.getX(), pressurePosition.getY(), 80, color, Color.TRANSPARENT, Shader.TileMode.CLAMP));
            canvas.drawCircle(pressurePosition.getX(), pressurePosition.getY(), 80, paint);
            paint.setXfermode(porterDuffXfermode);
        }
    }

    public void updatePressureData(PressureData pressureData) {
        Log.d(TAG, pressureData.toString());
        int[] pressures = pressureData.getData();
        for (int i = 0; i < 8; i++) {
            pressurePositions[i].setValue(pressures[i]);
        }
    }
}

