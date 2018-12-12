package com.joanna.footmassage.views.activities;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.joanna.footmassage.R;
import com.joanna.footmassage.modles.entities.PressureData;
import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.DiagnosisResultModel;
import com.joanna.footmassage.modles.models.StartDiagnosisModel;
import com.joanna.footmassage.modles.repositories.DiagnosisRetrofitRepository;
import com.joanna.footmassage.presenter.DiagnosisPresenter;
import com.joanna.footmassage.views.base.DiagnosisView;
import com.joanna.footmassage.views.base.FootDisplayView;

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

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        startBtn.setEnabled(true);
        finishBtn.setEnabled(false);
        diagnosisPresenter = new DiagnosisPresenter(new DiagnosisRetrofitRepository());
        diagnosisPresenter.setDiagnosisView(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        diagnosisPresenter.over();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            sureAboutFinishDialog();
        }
        return false;
    }

    private void sureAboutFinishDialog() {
        new AlertDialog.Builder(this)
                .setMessage("是否要離開此頁?")
                .setPositiveButton(R.string.confirm, (dialog, which) -> finish())
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        footDisplayView = new FootDisplayView(this, container.getMeasuredWidth(), container.getMeasuredHeight());
        footDisplayView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(footDisplayView);
    }

    public void onPainfulBtnClick(View view) {
        diagnosisPresenter.feltPainful(1);
    }

    public void onVeryPainfulBtnClick(View view) {
        diagnosisPresenter.feltPainful(2);
    }

    public void onStartBtnClick(View view) {
        diagnosisPresenter.findBluetoothDevice();
        startBtn.setEnabled(false);
        finishBtn.setEnabled(true);
    }

    public void onFinishBtnClick(View view) {
        diagnosisPresenter.over();
        DiagnosisResultModel diagnosisResultModel = new DiagnosisResultModel(user.getAccount(), user.getToken());
        diagnosisPresenter.getResult(diagnosisResultModel);
        startBtn.setEnabled(true);
        finishBtn.setEnabled(false);

//        // todo
//        DiagnosisResult result = new DiagnosisResult(1, "您的胃部疼痛指數偏高，建議您多加留意。", new Date());
//        showResultDialog(result);
    }

    private void showResultDialog(DiagnosisResult diagnosisResult) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("診斷結果");
        alertDialogBuilder.setMessage(diagnosisResult.getResult());
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
    public void onBluetoothDeviceConnectFailed() {
        new AlertDialog.Builder(this)
                .setMessage("連線失敗，請確認您的Arduino是否有開啟。")
                .setNegativeButton("OK", null)
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
    public void onDiagnosisResultReceivedSuccessfully(DiagnosisResult diagnosisResult) {
        showResultDialog(diagnosisResult);
    }

    @Override
    public void onDiagnosisResultReceivedFailed() {
        new AlertDialog.Builder(this)
                .setMessage("結果讀取失敗")
                .setNegativeButton("OK", null)
                .show();
    }

}