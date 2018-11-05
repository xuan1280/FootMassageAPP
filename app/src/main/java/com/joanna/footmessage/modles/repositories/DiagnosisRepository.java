package com.joanna.footmessage.modles.repositories;

import com.joanna.footmessage.modles.entities.DiagnosisResult;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.entities.User;
import com.joanna.footmessage.modles.models.DiagnosisResultModel;
import com.joanna.footmessage.modles.models.PressureDataModel;
import com.joanna.footmessage.modles.models.ResponseModel;

import java.io.IOException;
import java.util.List;


public interface DiagnosisRepository {
    ResponseModel<Integer> startDiagnosis(int id, String account, String token) throws IOException;
    ResponseModel<Integer> sendPressureData(PressureDataModel pressureDataModel) throws IOException;
    ResponseModel<DiagnosisResult> getDiagnosisResult(DiagnosisResultModel diagnosisResultModel) throws IOException;
}
