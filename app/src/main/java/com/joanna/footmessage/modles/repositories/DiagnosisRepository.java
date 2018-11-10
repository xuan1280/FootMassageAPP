package com.joanna.footmessage.modles.repositories;

import com.joanna.footmessage.modles.entities.DiagnosisResult;
import com.joanna.footmessage.modles.models.DiagnosisResultModel;
import com.joanna.footmessage.modles.models.PressureDataModel;
import com.joanna.footmessage.modles.models.ResponseIntModel;
import com.joanna.footmessage.modles.models.ResponseModel;
import com.joanna.footmessage.modles.models.StartDiagnosisModel;

import java.io.IOException;


public interface DiagnosisRepository {
    ResponseIntModel startDiagnosis(StartDiagnosisModel startDiagnosisModel) throws IOException;

    ResponseIntModel sendPressureData(PressureDataModel pressureDataModel) throws IOException;

    ResponseModel<DiagnosisResult> getDiagnosisResult(DiagnosisResultModel diagnosisResultModel) throws IOException;
}
