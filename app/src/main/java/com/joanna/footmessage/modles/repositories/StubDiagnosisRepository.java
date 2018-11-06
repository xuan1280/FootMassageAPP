package com.joanna.footmessage.modles.repositories;

import com.joanna.footmessage.modles.entities.DiagnosisResult;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.models.DiagnosisResultModel;
import com.joanna.footmessage.modles.models.PressureDataModel;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.StartDiagnosisModel;

import java.io.IOException;
import java.util.List;

public class StubDiagnosisRepository implements DiagnosisRepository {

    @Override
    public ResponseModel<Integer> startDiagnosis(StartDiagnosisModel startDiagnosisModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<Integer> sendPressureData(PressureDataModel pressureDataModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<DiagnosisResult> getDiagnosisResult(DiagnosisResultModel diagnosisResultModel) throws IOException {
        return null;
    }
}
