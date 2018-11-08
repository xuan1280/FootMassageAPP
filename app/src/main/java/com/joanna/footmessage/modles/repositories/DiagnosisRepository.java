package com.joanna.footmessage.modles.repositories;

import com.joanna.footmessage.modles.entities.DiagnosisResult;
import com.joanna.footmessage.modles.models.DiagnosisResultModel;
import com.joanna.footmessage.modles.models.PressureDataModel;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.StartDiagnosisModel;

import java.io.IOException;


public interface DiagnosisRepository {
    ResponseModel<Integer> startDiagnosis(StartDiagnosisModel startDiagnosisModel) throws IOException;

    ResponseModel<Integer> sendPressureData(PressureDataModel pressureDataModel) throws IOException;

    ResponseModel<DiagnosisResult> getDiagnosisResult(DiagnosisResultModel diagnosisResultModel) throws IOException;
}
