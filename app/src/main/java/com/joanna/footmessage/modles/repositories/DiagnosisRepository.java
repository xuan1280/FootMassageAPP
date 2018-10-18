package com.joanna.footmessage.modles.repositories;

import com.joanna.footmessage.modles.entities.DiagnosisResult;
import com.joanna.footmessage.modles.entities.PressureData;
import com.joanna.footmessage.modles.models.ResponseModel;

import java.io.IOException;
import java.util.List;


public interface DiagnosisRepository {
    ResponseModel<Integer> startDiagnosis(String token) throws IOException;
    ResponseModel<Integer> sendPressureData(String token, List<PressureData> pressureDataList) throws IOException;
    ResponseModel<DiagnosisResult> getDiagnosisResult(String token, int id) throws IOException;
}
