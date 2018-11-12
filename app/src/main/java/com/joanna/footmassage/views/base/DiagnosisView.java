package com.joanna.footmassage.views.base;

import android.bluetooth.BluetoothDevice;

import com.joanna.footmassage.modles.entities.PressureData;
import com.joanna.footmassage.modles.entities.Result;

public interface DiagnosisView {
    void onBluetoothDeviceHasFound(BluetoothDevice device);

    void onBluetoothDeviceNoFound();

    void onBluetoothDeviceConnectFailed();

    void onPressureDataReceived(PressureData pressureData);

    void onDiagnosisStarted(int rId);

    void onDiagnosisResultReceivedSuccessfully(Result result);
}
