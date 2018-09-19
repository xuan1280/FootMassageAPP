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
import android.view.View;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.MyCircle;

import java.util.ArrayList;
import java.util.List;


public class DiagnosisView extends View {
    private Context context;
    private Paint paint;
    private Bitmap bitmap;
    private int bitmapWidth, bitmapHeight;
    private PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DARKEN);
    private List<MyCircle> radialGradients = new ArrayList<>();
    private int[] colors = new int[] {Color.parseColor("#ffff4d4d"), Color.parseColor("#ccffff00"), Color.parseColor("#5500cc00")};
    private boolean drawing;


    public DiagnosisView(Context context) {
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

    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, float r) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(r, r);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    private void prepareRadialGradients(int bitmapWidth, int bitmapHeight) {
        float centerX = bitmapWidth / 2f;
        float centerY = bitmapHeight / 2f;
        float radius = 100;
        //radialGradients.add(new MyCircle(centerX + 200, centerY, radius, colors, new float[]{.0f, .7f, 1.0f}, Shader.TileMode.CLAMP));
        radialGradients.add(new MyCircle(300, centerY, radius, Color.RED, Color.TRANSPARENT, Shader.TileMode.CLAMP));
        radialGradients.add(new MyCircle(300, centerY+300, radius, Color.YELLOW, Color.TRANSPARENT, Shader.TileMode.CLAMP));
        radialGradients.add(new MyCircle(400, centerY, radius, Color.GREEN, Color.TRANSPARENT, Shader.TileMode.CLAMP));
        radialGradients.add(new MyCircle(750, 500, radius, Color.RED, Color.TRANSPARENT, Shader.TileMode.CLAMP));
        //radialGradients.add(new MyCircle(centerX + 200, centerY + 100, radius, colors, new float[]{.0f, .5f, 1f}, Shader.TileMode.MIRROR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        bitmap = zoomBitmap(bitmap, (float) canvasHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, (canvasWidth - bitmap.getWidth())/2, 0, paint);
        paint.setXfermode(porterDuffXfermode);
        for (MyCircle circle : radialGradients) {
            paint.setShader(circle);
            canvas.drawCircle(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), paint);
            paint.setXfermode(porterDuffXfermode);
        }
    }

    public void stopLoopingDrawing() {
        drawing = false;
    }
}

