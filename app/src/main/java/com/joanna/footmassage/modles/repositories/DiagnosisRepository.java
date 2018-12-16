package com.joanna.footmassage.modles.repositories;

import com.joanna.footmassage.modles.entities.DiagnosisResult;
import com.joanna.footmassage.modles.models.DiagnosisResultModel;
import com.joanna.footmassage.modles.models.PressureDataModel;
import com.joanna.footmassage.modles.models.ResponseIntModel;
import com.joanna.footmassage.modles.models.ResponseModel;
import com.joanna.footmassage.modles.models.StartDiagnosisModel;

import java.io.IOException;


public interface DiagnosisRepository {
    ResponseIntModel startDiagnosis(StartDiagnosisModel startDiagnosisModel) throws IOException;

    ResponseModel<Object> sendPressureData(PressureDataModel pressureDataModel) throws IOException;

    ResponseModel<DiagnosisResult> getDiagnosisResult(DiagnosisResultModel diagnosisResultModel) throws IOException;
}
