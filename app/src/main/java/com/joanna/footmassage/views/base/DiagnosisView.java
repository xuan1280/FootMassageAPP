package com.joanna.footmassage.views.base;

import android.bluetooth.BluetoothDevice;

import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.entities.PressureData;

public interface DiagnosisView {
    void onBluetoothDeviceHasFound(BluetoothDevice device);

    void onBluetoothDeviceNoFound();

    void onBluetoothDeviceConnectFailed();

    void onPressureDataReceived(PressureData pressureData);

    void onDiagnosisStarted(int rId);

    void onDiagnosisResultReceivedSuccessfully(DiagnosisResult diagnosisResult);

    void onDiagnosisResultReceivedFailed();
}
