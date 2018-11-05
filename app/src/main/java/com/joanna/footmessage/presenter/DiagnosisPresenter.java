package com.joanna.footmessage.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

import com.joanna.footmessage.modles.entities.PressureData;
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
    private String BTName = "BT-01";
    private BluetoothAdapter mBtAdapter;
    private BluetoothSocket socket;
    private Handler mHandler = new Handler();
    private List<PressureData> pressureDataList = new ArrayList<>();

    public DiagnosisPresenter(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;

    }

    public void setDiagnosisView(DiagnosisView diagnosisView) {
        this.diagnosisView = diagnosisView;
    }

    public void findBluetoothDevice() {
        // Get the local Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get a set of currently paired devices
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                Log.d(TAG,device.getName() + "\n" + device.getAddress());
                if (BTName.equals(device.getName())) {
                    diagnosisView.onBluetoothDeviceHasFound(device);
                }
            }
        } else {
            Log.d(TAG,"no device");
            diagnosisView.onBluetoothDeviceNoFound();
        }
    }

    public void connectDevice(BluetoothDevice device) {
        Log.d(TAG, "connect device " + device.getName());
        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);
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
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Date date = new Date(System.currentTimeMillis());
                                        PressureData pressureData = new PressureData(date, data);
                                        Log.d(TAG, data);
                                        pressureDataList.add(pressureData);
                                        diagnosisView.onPressureDataReceived(pressureData);
                                    }
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
