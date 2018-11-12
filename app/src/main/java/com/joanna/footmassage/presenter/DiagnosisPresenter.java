package com.joanna.footmassage.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

import com.joanna.footmassage.modles.entities.PressureData;
import com.joanna.footmassage.modles.entities.User;
import com.joanna.footmassage.modles.models.PressureDataModel;
import com.joanna.footmassage.modles.models.ResponseIntModel;
import com.joanna.footmassage.modles.models.StartDiagnosisModel;
import com.joanna.footmassage.modles.repositories.DiagnosisRepository;
import com.joanna.footmassage.views.base.DiagnosisView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DiagnosisPresenter {
    private static final String TAG = "DiagnosisPresenter";
    private DiagnosisView diagnosisView;
    private DiagnosisRepository diagnosisRepository;
    private Handler handler = new Handler();
    private BluetoothDevice device;
    private final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private List<PressureData> pressureDataList = new ArrayList<>();
    private Map<Integer, Integer> painfulIndexMap = new HashMap<>();
    private int rId;
    private boolean isFinished;

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
                Log.d(TAG, device.getName() + "\n" + device.getAddress());
                String BTName = "BT-01";
                if (BTName.equals(device.getName())) {
                    this.device = device;
                    if (isConnectDevice())
                        diagnosisView.onBluetoothDeviceHasFound(device);
                    else
                        diagnosisView.onBluetoothDeviceConnectFailed();
                }
            }
        } else {
            Log.d(TAG, "no device");
            diagnosisView.onBluetoothDeviceNoFound();
        }
    }

    public void startDiagnosis(StartDiagnosisModel startDiagnosisModel) {
        Log.d(TAG, "start diagnosis");
        new Thread() {
            @Override
            public void run() {
                ResponseIntModel responseModel;
                try {
                    responseModel = diagnosisRepository.startDiagnosis(startDiagnosisModel);
                    Log.d(TAG, responseModel.getMessage());
                    if (responseModel.getCode() == 0) {
                        handler.post(() -> {
                            rId = responseModel.getData();
                            diagnosisView.onDiagnosisStarted(rId);
                        });

                        connectDeviceAndReceiveData();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private boolean isConnectDevice() {
        UUID uuid = UUID.fromString(SPP_UUID);
        BluetoothSocket socket;
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid);
            return socket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void connectDeviceAndReceiveData() {
        Log.d(TAG, "connect device " + device.getName());
        UUID uuid = UUID.fromString(SPP_UUID);
        BluetoothSocket socket;
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid);
            socket.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if (socket.isConnected()) {
                Log.d(TAG, "connect device " + device.getName());
                new Thread(() -> {
                    Log.d(TAG, "receive message");
                    try {
                        while (socket.isConnected() && !isFinished) {
                            final String data = bufferedReader.readLine();
                            Log.d(TAG, data);
                            handler.post(() -> {
                                Date date = new Date(System.currentTimeMillis());
                                PressureData pressureData = new PressureData(date, data);
                                pressureDataList.add(pressureData);
                                diagnosisView.onPressureDataReceived(pressureData);
                            });
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void feltPainful(int painful) {
        painfulIndexMap.put(pressureDataList.size() - 1, painful);
    }

    private void sendPressureData(User user) {
//        new Thread(() -> {
//            try {
//                // todo
//                PressureDataModel pressureDataModel = new PressureDataModel(user.getAccount(), user.getToken(), rId, , pressureDataList.size());
//                ResponseIntModel responseIntModel = diagnosisRepository.sendPressureData(pressureDataModel);
//                Log.d(TAG, String.valueOf(responseIntModel.getData()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    private void getPressureDataSequences() {
        // todo
        int size = pressureDataList.size();
        int [] painfuls = new int[size];
        List<Integer> painfulIndexList = new ArrayList<>(painfulIndexMap.keySet());
        Collections.sort(painfulIndexList);
        for (int painfulIndex: painfulIndexList)
            if (painfulIndex > 10)
                for (int i = painfulIndex - 10; i <= painfulIndex; i++)
                    if (painfuls[i] < painfulIndexMap.get(painfulIndex))
                        painfuls[i] = painfulIndexMap.get(painfulIndex);
                    else
                        for (i = 0; i < painfulIndex; i++)
                            if (painfuls[i] < painfulIndexMap.get(painfulIndex))
                                painfuls[i] = painfulIndexMap.get(painfulIndex);
    }

    public void over() {
        isFinished = true;

    }
}
