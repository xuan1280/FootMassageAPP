package com.joanna.footmassage.modles.repositories;

import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.models.DiagnosisResultModel;
import com.joanna.footmassage.modles.models.PressureDataModel;
import com.joanna.footmassage.modles.models.ResponseIntModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.StartDiagnosisModel;

import java.io.IOException;

public class StubDiagnosisRepository implements DiagnosisRepository {

    @Override
    public ResponseIntModel startDiagnosis(StartDiagnosisModel startDiagnosisModel) throws IOException {
        String str = "{\n" +
                "    \"code\": 0,\n" +
                "    \"message\": \"查詢成功\",\n" +
                "    \"data\": {\"value\":132\n}}";
        return new ResponseIntModel(0, "成功", 1);
    }

    @Override
    public ResponseIntModel sendPressureData(PressureDataModel pressureDataModel) throws IOException {
        return null;
    }

    @Override
    public ResponseModel<DiagnosisResult> getDiagnosisResult(DiagnosisResultModel diagnosisResultModel) throws IOException {
        return null;
    }
}
