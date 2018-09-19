package com.joanna.footmessage.views.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.MyCircle;
import com.joanna.footmessage.views.base.DiagnosisView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiagnosisActivity extends AppCompatActivity {
    private String bt = "BT-01";
    @BindView(R.id.container) RelativeLayout container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        ButterKnife.bind(this);

        init();

    }

    private void init() {
        DiagnosisView diagnosisView = new DiagnosisView(this);
        diagnosisView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(diagnosisView);
        createAndShowDialogForAskingConnectingToBluetooth();
    }

    public void createAndShowDialogForAskingConnectingToBluetooth() {
        new AlertDialog.Builder(this)
                .setMessage("是否要連結至" + bt)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    public void onFinishBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(DiagnosisActivity.this , DiagnosticResultActivity.class);
        startActivity(intent);
    }
}