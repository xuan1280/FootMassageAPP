package com.joanna.footmessage.modles.repositories;

import com.joanna.footmessage.modles.entities.DiagnosisResult;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.models.ResponseModel;

import java.io.IOException;
import java.util.List;

public class StubDiagnosisRepository implements DiagnosisRepository {
    @Override
    public ResponseModel<Integer> startDiagnosis(String token) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<Integer> sendPressureData(String token, List<PressureData> pressureDataList) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<DiagnosisResult> getDiagnosisResult(String token, int id) throws IOException {
        return null;
    }
}
