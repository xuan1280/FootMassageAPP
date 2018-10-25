package com.joanna.footmessage.views.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.Log;
import android.view.View;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.PressureCircle;
import com.joanna.footmessage.modles.entities.PressureData;


public class FootDisplayView extends View {
    private static final String TAG = "FootDisplayView";
    private Context context;
    private Paint paint;
    private Bitmap bitmap;
    private int bitmapWidth, bitmapHeight;
    private PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
    private PressureCircle[] radialGradients = new PressureCircle[8];
    private int[] colors = new int[] {Color.parseColor("#ffff4d4d"), Color.parseColor("#ccffff00"), Color.parseColor("#5500cc00")};
    private boolean drawing;


    public FootDisplayView(Context context) {
        super(context);
        this.context = context;
        drawing = true;
        setBackgroundColor(Color.WHITE);
        init();
        createThreadForLoopingDrawing();
    }

    private void createThreadForLoopingDrawing() {
        new Thread(()-> {
            try {
                while(drawing) {
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
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        paint = new Paint();
        paint.setAntiAlias(true);
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
        prepareRadialGradients(bitmapWidth, bitmapHeight);
    }

    public Bitmap zoomBitmap(Bitmap bitmap, float r) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(r, r);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    // create eight circle set position
    private void prepareRadialGradients(int bitmapWidth, int bitmapHeight) {
        float centerX = this.getWidth() / 2f;
        float centerY = this.getHeight() / 2f;
        radialGradients[0] = new PressureCircle(0, centerX-315, centerY-60, Color.RED, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        radialGradients[1] = new PressureCircle(1, centerX-330, centerY, Color.BLUE, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        radialGradients[2] = new PressureCircle(2, centerX-300, centerY, Color.YELLOW, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        radialGradients[3] = new PressureCircle(3, centerX+60, centerY, Color.GREEN, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        radialGradients[4] = new PressureCircle(4, centerX, centerY-150, Color.LTGRAY, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        radialGradients[5] = new PressureCircle(5, centerX+60, centerY-150, Color.CYAN, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        radialGradients[6] = new PressureCircle(6, centerX, centerY-80, Color.GRAY, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        radialGradients[7] = new PressureCircle(7, centerX+60, centerY-80, Color.MAGENTA, Color.TRANSPARENT, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        bitmap = zoomBitmap(bitmap, (float) canvasHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, (canvasWidth - bitmap.getWidth())/2, 0, paint);
        paint.setXfermode(porterDuffXfermode);
        for (PressureCircle circle : radialGradients) {
            paint.setShader(circle);
            canvas.drawCircle(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), paint);
            paint.setXfermode(porterDuffXfermode);
        }
    }

    public void updatePressureData(PressureData pressureData) {
        Log.d(TAG, pressureData.toString());
        int[] pressures = pressureData.getData();
        for (int i = 0; i < 8; i++) {
            radialGradients[i].setValue(pressures[i]);
        }
    }

    public void stopLoopingDrawing() {
        drawing = false;
    }
}

