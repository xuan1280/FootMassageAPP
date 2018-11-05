package com.joanna.footmessage.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.StartDiagnosisModel;
import com.joanna.footmessage.modles.repositories.DiagnosisRepository;
import com.joanna.footmessage.views.activities.MainActivity;
import com.joanna.footmessage.views.base.DiagnosisView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DiagnosisPresenter {
    private static final String TAG = "DiagnosisPresenter";
    private DiagnosisView diagnosisView;
    private DiagnosisRepository diagnosisRepository;
    private Handler handler = new Handler();
    private BluetoothDevice device;
    private List<PressureData> pressureDataList = new ArrayList<>();
    private int rId;

    public DiagnosisPresenter(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public void setDiagnosisView(DiagnosisView diagnosisView) {
        this.diagnosisView = diagnosisView;
    }

    public void findBluetoothDevice() {
        BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                Log.d(TAG,device.getName() + "\n" + device.getAddress());
                String BTName = "BT-01";
                if (BTName.equals(device.getName())) {
                    this.device = device;
                    diagnosisView.onBluetoothDeviceHasFound(device);
                }
            }
        } else {
            Log.d(TAG,"no device");
            diagnosisView.onBluetoothDeviceNoFound();
        }
    }

    public void startDiagnosis(StartDiagnosisModel startDiagnosisModel) {
        Log.d(TAG,"start diagnosis");
        new Thread(){
            @Override
            public void run() {
                ResponseModel responseModel;
                try {
                    responseModel = diagnosisRepository.startDiagnosis(startDiagnosisModel);
                    Log.d(TAG, responseModel.getMessage());
                    if (responseModel.getCode() == 200) {
                        handler.post(() -> diagnosisView.onDiagnosisStarted());
                        rId = (int) responseModel.getData();
                        connectDeviceAndReceiveData();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void connectDeviceAndReceiveData() {
        Log.d(TAG, "connect device " + device.getName());
        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);
        BluetoothSocket socket;
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid);
            socket.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if (socket.isConnected()) {
                Log.d(TAG,"connect device " + device.getName());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "receive message");
                        try {
                            while (socket.isConnected()) {
                                final String data = bufferedReader.readLine();
                                handler.post(() -> {
                                    Date date = new Date(System.currentTimeMillis());
                                    PressureData pressureData = new PressureData(date, data);
                                    Log.d(TAG, data);
                                    pressureDataList.add(pressureData);
                                    diagnosisView.onPressureDataReceived(pressureData);
                                });
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
