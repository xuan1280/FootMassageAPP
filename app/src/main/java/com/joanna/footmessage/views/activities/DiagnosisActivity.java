package com.joanna.footmessage.views.activities;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.joanna.footmessage.R;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.entities.Result;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.StartDiagnosisModel;
import com.joanna.footmessage.modles.repositories.DiagnosisRetrofitRepository;
import com.joanna.footmessage.modles.repositories.StubDiagnosisRepository;
import com.joanna.footmessage.presenter.DiagnosisPresenter;
import com.joanna.footmessage.views.base.DiagnosisView;
import com.joanna.footmessage.views.base.FootDisplayView;

import java.util.Date;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiagnosisActivity extends AppCompatActivity implements DiagnosisView {
    private static final String TAG = "DiagnosisActivity";
    private User user;
    private DiagnosisPresenter diagnosisPresenter;
    private FootDisplayView footDisplayView;
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.startBtn)
    Button startBtn;
    @BindView(R.id.finishBtn)
    Button finishBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        diagnosisPresenter.over();
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
        diagnosisPresenter = new DiagnosisPresenter(new DiagnosisRetrofitRepository());
        diagnosisPresenter.setDiagnosisView(this);
    }

    public void onPainfulBtnClick(View view) {
        // TODO:
        diagnosisPresenter.createSequenceAndSend(user, 1);
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
        // todo
        diagnosisPresenter.over();
        startBtn.setEnabled(true);
        finishBtn.setEnabled(false);
        Result result = new Result(1, "您的胃部疼痛指數偏高，建議您多加留意。", new Date());
        showResultDialog(result);
    }

    private void showResultDialog(Result result) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("診斷結果");
        alertDialogBuilder.setMessage(result.getResult());
        alertDialogBuilder.show();
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
        startBtn.setEnabled(true);
    }

    @Override
    public void onPressureDataReceived(PressureData pressureData) {
        Log.d(TAG, pressureData.toString());
        footDisplayView.updatePressureData(pressureData);
    }

    @Override
    public void onDiagnosisStarted(int rId) {
        Log.d(TAG, "onDiagnosisStarted: " + rId);
    }

    @Override
    public void onDiagnosisResultReceivedSuccessfully(Result result) {
        showResultDialog(result);
    }

}