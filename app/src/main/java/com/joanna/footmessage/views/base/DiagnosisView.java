package com.joanna.footmessage.views.base;

import android.bluetooth.BluetoothDevice;

import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.entities.Result;

public interface DiagnosisView {
    void onBluetoothDeviceHasFound(BluetoothDevice device);

    void onBluetoothDeviceNoFound();

    void onPressureDataReceived(PressureData pressureData);

    void onDiagnosisStarted(int rId);

    void onDiagnosisResultReceivedSuccessfully(Result result);
}
