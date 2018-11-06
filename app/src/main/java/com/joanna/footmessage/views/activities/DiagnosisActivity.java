package com.joanna.footmessage.views.activities;

import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.StartDiagnosisModel;
import com.joanna.footmessage.modles.repositories.DiagnosisRetrofitRepository;
import com.joanna.footmessage.modles.repositories.StubDiagnosisRepository;
import com.joanna.footmessage.presenter.DiagnosisPresenter;
import com.joanna.footmessage.views.base.FootDisplayView;
import com.joanna.footmessage.views.base.DiagnosisView;

import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiagnosisActivity extends AppCompatActivity implements DiagnosisView {
    private static final String TAG = "DiagnosisActivity";
    private User user;
    private DiagnosisPresenter diagnosisPresenter;
    private FootDisplayView footDisplayView;
    @BindView(R.id.container) RelativeLayout container;
    @BindView(R.id.startBtn) Button startBtn;
    @BindView(R.id.finishBtn) Button finishBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        footDisplayView = new FootDisplayView(this, container.getMeasuredWidth(), container.getMeasuredHeight());
        footDisplayView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(footDisplayView);
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        startBtn.setEnabled(true);
        finishBtn.setEnabled(false);

        diagnosisPresenter = new DiagnosisPresenter(new StubDiagnosisRepository());
        diagnosisPresenter.setDiagnosisView(this);
    }

    private void test() {
        Handler handler = new Handler();
        new Thread(() ->{
            for (int i = 0; i < 20; i++) {
                try {
                    handler.post(() ->{
                        Date date = new Date(System.currentTimeMillis());
                        int[] data = new int[8];
                        for (int j = 0; j < 8; j++)
                            data[j] = new Random().nextInt(45);
                        PressureData pressureData = new PressureData(date, data);
                        footDisplayView.updatePressureData(pressureData);
                    });
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

    }

    public void onPainfulBtnClick(View view) {
        // TODO:
    }

    public void onVeryPainfulBtnClick(View view) {
        // TODO:
    }

    public void onStartBtnClick(View view) {
        diagnosisPresenter.findBluetoothDevice();
        startBtn.setEnabled(false);
        finishBtn.setEnabled(true);
    }

    public void onFinishBtnClick(View view) {
        Intent intent = new Intent();
        intent.setClass(DiagnosisActivity.this , DiagnosticResultActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBluetoothDeviceHasFound(BluetoothDevice device) {
        new AlertDialog.Builder(this)
                .setMessage("是否要連結至" + device.getName() + "?")
                .setNegativeButton("OK", (dialog, which) -> {
                    StartDiagnosisModel startDiagnosisModel = new StartDiagnosisModel(user.getId(), user.getAccount(), user.getToken());
                    diagnosisPresenter.startDiagnosis(startDiagnosisModel);
                })
                .show();
    }

    @Override
    public void onBluetoothDeviceNoFound() {
        new AlertDialog.Builder(this)
                .setMessage("請先開啟藍芽，並連結至BT-01")
                .setNegativeButton("OK", (dialog, which) -> {
                    Intent intent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
                    startActivity(intent);
                })
                .show();
    }

    @Override
    public void onPressureDataReceived(PressureData pressureData) {
        Log.d(TAG, pressureData.toString());
        footDisplayView.updatePressureData(pressureData);
    }

    @Override
    public void onDiagnosisStarted() {
        Log.d(TAG, "onDiagnosisStarted");
    }

}